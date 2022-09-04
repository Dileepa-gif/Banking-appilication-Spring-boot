package com.EAD.banking.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EAD.banking.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer>{
	List<Loan> findByCustomerId(int id);
}
