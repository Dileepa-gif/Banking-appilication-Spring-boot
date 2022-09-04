package com.EAD.banking.service;

import java.util.List;
import java.util.Set;

import com.EAD.banking.model.Account;
import com.EAD.banking.model.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomers(); 
	void saveCustomer(Customer customer);
	Customer getCustomerById(int id);
	void updateCustomer(int id,Customer customer);
	void deleteCustomerById(int id);
	
	
	
}
