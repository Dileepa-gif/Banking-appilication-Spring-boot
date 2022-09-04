package com.EAD.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.EAD.banking.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	List<Transaction> findByAccountId(int id);
}
