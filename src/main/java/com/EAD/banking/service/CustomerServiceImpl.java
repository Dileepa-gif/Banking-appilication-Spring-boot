package com.EAD.banking.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EAD.banking.model.Account;
import com.EAD.banking.model.Customer;
import com.EAD.banking.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository; 
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@Override
	public void saveCustomer(Customer customer) {
		this.customerRepository.save(customer);
	
	}

	@Override
	public Customer getCustomerById(int id) {
		Optional<Customer> optional= customerRepository.findById(id);
		Customer customer= null;
		if(optional.isPresent()) {
			customer= optional.get();
		}else {
			throw new RuntimeException(" Customer not found for id : "+id);
		}
		return customer;
	}
	
	@Override
	public void updateCustomer(int id, Customer customer) {
		Customer existingCustomer=customerRepository.findById(id).orElse(null);
		existingCustomer.setName(customer.getName());
		existingCustomer.setPhone(customer.getPhone());
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setNic(customer.getNic());
		this.customerRepository.save(existingCustomer);
	}

	@Override
	public void deleteCustomerById(int id) {
		this.customerRepository.deleteById(id);
		
	}

	
}
