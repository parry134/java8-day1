package com.example.service;

import org.apache.log4j.Logger;

import com.example.model.Account;
import com.example.repository.AccountRepository;

/*
 *   design & performance issues
 *   ---------------------------
 *   
 *   => tight-coupling b/w dependent & dependency
 *   
 *   	-> can't extent/modify any feature
 *   	
 *   => too many duplicate dependency instances were created & discarded
 *   
 *   	-> slow, memory/resource usage high
 *   
 *   => unit-testing not possible
 *   
 *   	-> dev / bug-fix slow
 *   
 *   
 *   -----------------------------------------------------
 *   
 *   
 *   why these issues ?
 *   
 *   
 *   => dependent object creating it's own dependency
 *   
 *   
 *   soln :
 *   
 *   => don't create , get from factory   ==> factory-design-pattern
 *   
 *   
 *   limitation on factory-lookup:	
 *   
 *   => factory-location tight-coupling
 *   
 *   best-soln:
 *   
 *   	=> don't create/lookup , get by 'third-party'  ==> Inversion-Of-Control ( IOC )
 *   
 *   
 *   
 *   how to implement IOC ?
 *   
 *    2 ways
 *    
 *    	=> dependency injection ( DI )
 *    
 *    		- constructor DI
 *    
 *    		- setter
 *   
 * 
 */

public class TxrServiceImpl implements TxrService {

	private static Logger logger = Logger.getLogger("bank");
	
	private AccountRepository accountRepository;

	public TxrServiceImpl(AccountRepository accountRepository) {
		this.accountRepository=accountRepository;
		logger.info("TxrServiceImpl instance created");
	}
	
	

	/* (non-Javadoc)
	 * @see com.example.service.TxrService#txr(double, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean txr(double amount, String fromAccNum, String toAccNum) {
		logger.info("Txr-begin");
		// load account(s)
		Account fromAccount = accountRepository.loadAccount(fromAccNum);
		Account toAccount = accountRepository.loadAccount(toAccNum);
		// debit & credit
		// update account(s)
		accountRepository.updateAccount(fromAccount);
		accountRepository.updateAccount(toAccount);
		logger.info("Txr-success");
		return true;
	}

}
