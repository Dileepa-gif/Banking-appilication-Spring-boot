package com.EAD.banking.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EAD.banking.model.Account;
import com.EAD.banking.model.Customer;
import com.EAD.banking.repository.AccountRepository;
import com.EAD.banking.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	


	@Override
	public List<Account> getAccountsByCustomerId(int id) {
		return accountRepository.findByCustomerId(id);
	}



	@Override
	public void saveAccount(Account account) {
		this.accountRepository.save(account);
	}



	@Override
	public Account getAccountById(int id) {
		Optional<Account> optional= accountRepository.findById(id);
		Account account= null;
		if(optional.isPresent()) {
			account= optional.get();
		}else {
			throw new RuntimeException(" Account not found for id : "+id);
		}
		return account;
	}



	@Override
	public void updateAccount(int id, Account account) {
		Account existingAccount=accountRepository.findById(id).orElse(null);
		existingAccount.setType(account.getType());
		existingAccount.setBalance(account.getBalance());
		this.accountRepository.save(existingAccount);
		
	}



	@Override
	public void deleteAccountById(int id) {
		this.accountRepository.deleteById(id);
	}

	
	
	

}
