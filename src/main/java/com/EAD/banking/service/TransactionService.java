package com.EAD.banking.service;

import java.util.List;

import com.EAD.banking.model.Transaction;

public interface TransactionService {
	List<Transaction> getTransactionsByAccountId(int id);
	void saveTransaction(Transaction transaction);
	Transaction getTransactionById(int id);
	void updateTransaction(int id, Transaction transaction);
	void deleteTransactionById(int id);

}
