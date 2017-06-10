package com.keypot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.keypot.app.entity.Customer;
import com.keypot.app.entity.UserAccount;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByAccountNumber(Long accountNumber);

	// void findByUserAccountId(UserAccount userAccount);

}
