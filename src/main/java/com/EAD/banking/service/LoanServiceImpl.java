package com.EAD.banking.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EAD.banking.model.Loan;
import com.EAD.banking.model.Customer;
import com.EAD.banking.repository.LoanRepository;
import com.EAD.banking.repository.CustomerRepository;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanRepository loanRepository;
	
	


	@Override
	public List<Loan> getLoansByCustomerId(int id) {
		return loanRepository.findByCustomerId(id);
	}



	@Override
	public void saveLoan(Loan loan) {
		this.loanRepository.save(loan);
	}



	@Override
	public Loan getLoanById(int id) {
		Optional<Loan> optional= loanRepository.findById(id);
		Loan loan= null;
		if(optional.isPresent()) {
			loan= optional.get();
		}else {
			throw new RuntimeException(" Loan not found for id : "+id);
		}
		return loan;
	}



	@Override
	public void updateLoan(int id, Loan loan) {
		Loan existingLoan=loanRepository.findById(id).orElse(null);
		if(loan.getCurrentAmount()<=loan.getTotalAmount()) {
			existingLoan.setType(loan.getType());
			existingLoan.setCurrentAmount(loan.getCurrentAmount());
			existingLoan.setTotalAmount(loan.getTotalAmount());
			this.loanRepository.save(existingLoan);
		}else {
			throw new RuntimeException(" Something Wrong ");
		}
	
	}



	@Override
	public void deleteLoanById(int id) {
		this.loanRepository.deleteById(id);
	}

	
	
	

}
