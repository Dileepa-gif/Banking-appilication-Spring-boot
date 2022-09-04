package com.EAD.banking.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.EAD.banking.model.Account;
import com.EAD.banking.model.Customer;
import com.EAD.banking.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "allCustomers";
	}
	
	@GetMapping("/newCustomerForm")
	public String showNewCustomerForm(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer", customer);
		return "newCustomerForm";
	}
	
	@PostMapping("/saveNewCustomer")
	public String saveNewCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customers";
	}
	
	@GetMapping("/showCustomerUpdatingForm/{id}")
	public String showCustomerUpdatingForm(@PathVariable( value = "id") int id, Model model) {
		Customer customer =customerService.getCustomerById(id);
		model.addAttribute("customer",customer);
		return "customerUpdatingForm" ;
	}
	
	@PostMapping("/updateCustomer/{id}")
	public String updateCustomer(@PathVariable( value = "id") int id, @ModelAttribute("customer") Customer customer) {
		customerService.updateCustomer(id,customer);
		return "redirect:/customers";
	}
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable (value= "id") int id) {
		this.customerService.deleteCustomerById(id);
		return "redirect:/customers";
	}
	
	
	
	
	
	
}
