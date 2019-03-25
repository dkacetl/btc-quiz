package com.openmatics.quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.*;

@Component
public class WalletService {

    private final int GROUPS_CNT = 3;

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletService.class);

    private Set<String>[] walletsGroups = new Set[GROUPS_CNT];

    @Autowired
    private ResourceLoader resourceLoader;

    public String hashWallet(int hour, String something) {
        String[] walletIds = getWalletsGroup(hour);
        return walletIds[Math.abs(something.hashCode()) % walletIds.length];
    }

    private String[] getWalletsGroup(int hour) {
        int group = Math.abs(hour) % GROUPS_CNT;
        return walletsGroups[group].toArray(new String[] {});
    }

    @PostConstruct
    private void init() throws IOException {
        loadWallets();
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public void loadWallets() throws IOException {
        Resource[] wallets = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath:/static/wallets/*");

        int index = 0;
        for (Resource wallet: wallets) {
            LOGGER.info("Loaded wallet:" + wallet.getFilename());
            if (walletsGroups[index%GROUPS_CNT]==null) {
                walletsGroups[index%GROUPS_CNT] = new HashSet<>();
            }
            walletsGroups[index%GROUPS_CNT].add(wallet.getFilename());
            index++;
        }
    }
}
