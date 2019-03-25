package com.openmatics.quiz;

import com.openmatics.quiz.service.WalletService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizApplicationTests {

	@Autowired
	private WalletService walletService;

	@Test
	public void contextLoads() {
		String walleta = walletService.hashWallet(10,"a");
		String walletb = walletService.hashWallet(11,"ASda");
		String walletc = walletService.hashWallet(12,"fdsa");
		String walletd = walletService.hashWallet(13,"zxccxzb");
		String walletx = walletService.hashWallet(1,"asdb");
		String wallete = walletService.hashWallet(123,"ADb");
		String walletf = walletService.hashWallet(133,"adsdsab");
		String walletg = walletService.hashWallet(0,"b");

		for (int i=0; i<1000; i++) {
			walletService.hashWallet(new Random().nextInt(), "" + new Random().nextDouble());
		}
	}

}
