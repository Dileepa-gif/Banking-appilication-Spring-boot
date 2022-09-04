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
import com.EAD.banking.model.Transaction;
import com.EAD.banking.service.AccountService;
import com.EAD.banking.service.CustomerService;
import com.EAD.banking.service.TransactionService;

@Controller
public class TransactionController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/transactionOfAccount/{id}")
	public String getAllTransactionsByAccountId(@PathVariable( value = "id") int accountId,Model model) {
		model.addAttribute("listTransactionsByAccount", transactionService.getTransactionsByAccountId(accountId));
		model.addAttribute("customer", customerService.getCustomerById(accountService.getAccountById(accountId).getCustomer().getId()));
		model.addAttribute("account", accountService.getAccountById(accountId));
		return "transactionsOfAccount";
	}
	
	@GetMapping("/newTransactionForm/{id}")
	public String showNewTransactionForm(@PathVariable( value = "id") int accountId,Model model) {
		Transaction transaction=new Transaction();
		model.addAttribute("transaction", transaction);
		model.addAttribute("account", accountService.getAccountById(accountId));
		return "newTransactionForm";
	}
	
	@PostMapping("/saveNewTransaction/{id}")
	public String saveNewTransaction(@PathVariable( value = "id") int accountId, @ModelAttribute("transaction") Transaction transaction) {
		Account account= accountService.getAccountById(accountId);
		transaction.setAccount(account);
		transactionService.saveTransaction(transaction);
		if(transaction.isStatus()) {
			account.setBalance(account.getBalance()+transaction.getAmount());
		}else {
			account.setBalance(account.getBalance()-transaction.getAmount());
		}
		accountService.saveAccount(account);
		return "redirect:/transactionOfAccount/"+accountId;
	}
	
	@GetMapping("/showTransactionUpdatingForm/{id}")
	public String showTransactionUpdatingForm(@PathVariable( value = "id") int id, Model model) {
		Transaction transaction =transactionService.getTransactionById(id);
		model.addAttribute("transaction",transaction);
		return "transactionUpdatingForm" ;
	}
	
	@PostMapping("/updateTransaction/{id}")
	public String updateTransaction(@PathVariable( value = "id") int id, @ModelAttribute("transaction") Transaction transaction) {
		Transaction existingTransaction =transactionService.getTransactionById(id);
		Account account=existingTransaction.getAccount();
		if(existingTransaction.isStatus()) {
			account.setBalance(account.getBalance()-existingTransaction.getAmount());
		}else {
			account.setBalance(account.getBalance()+existingTransaction.getAmount());
		}
		transactionService.updateTransaction(id, transaction);
		if(transaction.isStatus()) {
			account.setBalance(account.getBalance()+transaction.getAmount());
		}else {
			account.setBalance(account.getBalance()-transaction.getAmount());
		}
		accountService.saveAccount(account);
		return "redirect:/transactionOfAccount/"+ transactionService.getTransactionById(id).getAccount().getId();
	}
	
	@GetMapping("/deleteTransaction/{id}")
	public String deleteTransaction(@PathVariable (value= "id") int id) {
		int accountId=transactionService.getTransactionById(id).getAccount().getId();
		Transaction existingTransaction =transactionService.getTransactionById(id);
		Account account=existingTransaction.getAccount();
		if(existingTransaction.isStatus()) {
			account.setBalance(account.getBalance()-existingTransaction.getAmount());
		}else {
			account.setBalance(account.getBalance()+existingTransaction.getAmount());
		}
		this.transactionService.deleteTransactionById(id);
//		accountService.saveAccount(account);
		return "redirect:/transactionOfAccount/"+accountId;
	}
}
