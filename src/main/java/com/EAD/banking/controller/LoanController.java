package com.EAD.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.EAD.banking.model.Loan;
import com.EAD.banking.model.Customer;
import com.EAD.banking.service.LoanService;
import com.EAD.banking.service.CustomerService;

@Controller
public class LoanController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/loanOfCustomer/{id}")
	public String getAllLoansByCustomerId(@PathVariable( value = "id") int customerId,Model model) {
		model.addAttribute("listLoansOfCustomer", loanService.getLoansByCustomerId(customerId));
		model.addAttribute("customer", customerService.getCustomerById(customerId));
		return "loansOfCustomer";
	}
	
	@GetMapping("/newLoanForm/{id}")
	public String showNewLoanForm(@PathVariable( value = "id") int customerId,Model model) {
		Loan loan=new Loan();
		model.addAttribute("loan", loan);
		model.addAttribute("customer", customerService.getCustomerById(customerId));
		return "newLoanForm";
	}
	
	@PostMapping("/saveNewLoan/{id}")
	public String saveNewLoan(@PathVariable( value = "id") int customerId, @ModelAttribute("loan") Loan loan) {
		Customer customer= customerService.getCustomerById(customerId);
		loan.setCustomer(customer);
		loan.setCurrentAmount(loan.getTotalAmount());
		loanService.saveLoan(loan);
		return "redirect:/loanOfCustomer/"+customerId;
	}
	
	@GetMapping("/showLoanUpdatingForm/{id}")
	public String showLoanUpdatingForm(@PathVariable( value = "id") int id, Model model) {
		Loan loan =loanService.getLoanById(id);
		model.addAttribute("loan",loan);
		return "loanUpdatingForm" ;
	}
	
	@PostMapping("/updateLoan/{id}")
	public String updateLoan(@PathVariable( value = "id") int id, @ModelAttribute("loan") Loan loan) {
		loanService.updateLoan(id, loan);
		return "redirect:/loanOfCustomer/"+ loanService.getLoanById(id).getCustomer().getId();
	}
	
	@GetMapping("/deleteLoan/{id}")
	public String deleteLoan(@PathVariable (value= "id") int id) {
		int customerId=loanService.getLoanById(id).getCustomer().getId();
		this.loanService.deleteLoanById(id);
		return "redirect:/loanOfCustomer/"+customerId;
	}
}
