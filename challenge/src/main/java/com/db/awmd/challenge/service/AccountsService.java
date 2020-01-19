package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }
  
  
  public Account moneyTransfer(Double amount,Double fee ,Account sourceAccount,Account destinationAccount)
  {
	  destinationAccount.setMoney(destinationAccount.getAmount()+amount);
	  destinationAccount.setSourceAccountId(sourceAccount.getAccountId());
	  accountsRepository.saveOrUpdate(destinationAccount);
	  sourceAccount.setMoney(sourceAccount.getAmount()-amount);
	  accountsRepository.saveOrUpdate(destinationAccount);
	  accountsRepository.saveOrUpdate(sourceAccount);
	  return sourceAccount;
	    
  }
}
