package com.EAD.banking.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EAD.banking.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	List<Account> findByCustomerId(int id);
}
