package com.EAD.banking.service;

import java.util.List;

import com.EAD.banking.model.Loan;


public interface LoanService {
	
	List<Loan> getLoansByCustomerId(int id);
	void saveLoan(Loan loan);
	Loan getLoanById(int id);
	void updateLoan(int id, Loan loan);
	void deleteLoanById(int id);
}
