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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class WalletService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletService.class);

    private Map<String, Resource> wallets = new HashMap<>();

    @Autowired
    private ResourceLoader resourceLoader;

    public String randomWallet() {
        String[] walletIds = wallets.keySet().toArray(new String[] {});
        return walletIds[new Random().nextInt() % walletIds.length];
    }

    public Map<String, Resource> getWallets() {
        return wallets;
    }

    @PostConstruct
    private void init() throws IOException {
        wallets = loadWallets();
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public Map<String, Resource> loadWallets() throws IOException {
        Resource[] wallets = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath:/static/wallets/*");

        Map<String, Resource> result = new HashMap<>();

        for (Resource wallet: wallets) {
            LOGGER.info("Loaded wallet:" + wallet.getFilename());
            result.put(wallet.getFilename(), wallet);
        }
        return result;
    }
}
