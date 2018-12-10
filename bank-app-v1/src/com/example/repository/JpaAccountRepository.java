package com.example.repository;

import org.apache.log4j.Logger;

import com.example.model.Account;

public class JpaAccountRepository {

	private Logger logger = Logger.getLogger("bank");

	public JpaAccountRepository() {
		logger.info("JpaAccountRepository instance created");
	}

	public Account loadAccount(String num) {
		// ..
		logger.info("loading account " + num);
		return null;
	}

	public void updateAccount(Account account) {
		logger.info("updating account");
		// ..
	}

}
