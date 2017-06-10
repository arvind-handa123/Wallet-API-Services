package com.keypot.app.service;

import com.keypot.app.entity.Customer;
import com.keypot.app.entity.UserAccount;

public interface CustomerService {

	Long createAccount(String customerName, String email);

	UserAccount addAmount(Long accountNumber, Double amount);

	UserAccount withdrawAmount(Long accountNumber, Double amount);

	UserAccount getBalance(Long accountNumber);

	Customer getCustomerDetail(UserAccount userAccount);

}
