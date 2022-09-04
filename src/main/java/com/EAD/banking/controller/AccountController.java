package com.EAD.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.EAD.banking.model.Account;
import com.EAD.banking.model.Customer;
import com.EAD.banking.service.AccountService;
import com.EAD.banking.service.CustomerService;

@Controller
public class AccountController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/accountOfCustomer/{id}")
	public String getAllAccountsByCustomerId(@PathVariable( value = "id") int customerId,Model model) {
		model.addAttribute("listAccountsOfCustomer", accountService.getAccountsByCustomerId(customerId));
		model.addAttribute("customer", customerService.getCustomerById(customerId));
		return "accountsOfCustomer";
	}
	
	@GetMapping("/newAccountForm/{id}")
	public String showNewAccountForm(@PathVariable( value = "id") int customerId,Model model) {
		Account account=new Account();
		model.addAttribute("account", account);
		model.addAttribute("customer", customerService.getCustomerById(customerId));
		return "newAccountForm";
	}
	
	@PostMapping("/saveNewAccount/{id}")
	public String saveNewAccount(@PathVariable( value = "id") int customerId, @ModelAttribute("account") Account account) {
		Customer customer= customerService.getCustomerById(customerId);
		account.setCustomer(customer);
		accountService.saveAccount(account);
		return "redirect:/accountOfCustomer/"+customerId;
	}
	
	@GetMapping("/showAccountUpdatingForm/{id}")
	public String showAccountUpdatingForm(@PathVariable( value = "id") int id, Model model) {
		Account account =accountService.getAccountById(id);
		model.addAttribute("account",account);
		return "accountUpdatingForm" ;
	}
	
	@PostMapping("/updateAccount/{id}")
	public String updateAccount(@PathVariable( value = "id") int id, @ModelAttribute("account") Account account) {
		accountService.updateAccount(id, account);
		return "redirect:/accountOfCustomer/"+ accountService.getAccountById(id).getCustomer().getId();
	}
	
	@GetMapping("/deleteAccount/{id}")
	public String deleteAccount(@PathVariable (value= "id") int id) {
		int customerId=accountService.getAccountById(id).getCustomer().getId();
		this.accountService.deleteAccountById(id);
		return "redirect:/accountOfCustomer/"+customerId;
	}
}
