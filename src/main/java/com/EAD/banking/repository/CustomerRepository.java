package com.EAD.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EAD.banking.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
