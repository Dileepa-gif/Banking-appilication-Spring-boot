package com.EAD.banking.service;



import java.util.List;

import com.EAD.banking.model.Account;


public interface AccountService {
	
	List<Account> getAccountsByCustomerId(int id);
	void saveAccount(Account account);
	Account getAccountById(int id);
	void updateAccount(int id, Account account);
	void deleteAccountById(int id);
}
