package com.EAD.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EAD.banking.model.Account;
import com.EAD.banking.model.Transaction;
import com.EAD.banking.repository.CustomerRepository;
import com.EAD.banking.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> getTransactionsByAccountId(int id) {
		return transactionRepository.findByAccountId(id);
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		this.transactionRepository.save(transaction);
		
	}

	@Override
	public Transaction getTransactionById(int id) {
		Optional<Transaction> optional= transactionRepository.findById(id);
		Transaction transaction= null;
		if(optional.isPresent()) {
			transaction= optional.get();
		}else {
			throw new RuntimeException(" Transaction not found for id : "+id);
		}
		return transaction;
	}

	@Override
	public void updateTransaction(int id, Transaction transaction) {
		Transaction existingTransaction=transactionRepository.findById(id).orElse(null);
		existingTransaction.setDescription(transaction.getDescription());
		existingTransaction.setAmount(transaction.getAmount());
		existingTransaction.setStatus(transaction.isStatus());
		this.transactionRepository.save(existingTransaction);
		
	}

	@Override
	public void deleteTransactionById(int id) {
		this.transactionRepository.deleteById(id);
	}

}
