package com.keypot.app.service.impl;

import java.util.Random;

import javax.transaction.Transactional;

import org.hibernate.cache.spi.UpdateTimestampsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keypot.app.entity.Customer;
import com.keypot.app.entity.UserAccount;
import com.keypot.app.entity.UserAccount.TraxnStatus;
import com.keypot.app.entity.UserAccount.TraxnType;
import com.keypot.app.repository.CustomerRepository;
import com.keypot.app.repository.UserAccountRepository;
import com.keypot.app.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	private final int MINIMUM_BALANCE = 0;
	private final int MAX_VALUE = 100000;

	@Override
	public Long createAccount(String customerName, String email) {
		Long accountNumber = getAccountNumber(new Random().nextInt(MAX_VALUE));
		UserAccount userAccount = createAccount(accountNumber);
		Customer customer = null;
		if (userAccount != null)
			customer = createUser(customerName, email, accountNumber);
		return customer.getId();

	}

	@Transactional
	private UserAccount createAccount(Long accountNumber) {
		UserAccount userAccount = new UserAccount();
		userAccount.setAccountNumber(accountNumber);
		userAccount.setAvailableBalance(0d);
		userAccountRepository.save(userAccount);

		return userAccount;
	}

	@Transactional
	private Customer createUser(String customerName, String email, Long accountNumber) {

		Customer customer = new Customer();
		customer.setCustomerName(customerName);
		customer.setEmailId(email);
		customer.setAccountNumber(accountNumber);
		customerRepository.save(customer);

		return customer;
	}

	private Long getAccountNumber(int id) {

		long accountNumber = Long
				.parseLong(String.valueOf(System.currentTimeMillis()).substring(1, 10).concat(String.valueOf(id)));

		return accountNumber;
	}

	@Override
	public UserAccount addAmount(Long accountNumber, Double amount) {

		UserAccount userAccount = userAccountRepository.findOne(accountNumber);
		if (userAccount != null) {
			/*
			 * add amount to available balance and update userAccount
			 */
			Double availableBalance = userAccount.getAvailableBalance() + amount;
			userAccount = UpdateAccount(userAccount, amount, availableBalance, TraxnType.CREDIT);
		}
		return userAccount;

	}

	@Transactional
	private UserAccount UpdateAccount(UserAccount userAccount, Double amount, Double availableBalance,
			TraxnType tranasactionType) {
		userAccount.setAvailableBalance(availableBalance);
		if (TraxnType.CREDIT.equals(tranasactionType)) {
			userAccount.setCreditedAmount(amount);
			userAccount.setTraxnType(TraxnType.CREDIT);
		} else if (TraxnType.DEBIT.equals(tranasactionType)) {
			userAccount.setDebitedAmount(amount);
			userAccount.setTraxnType(TraxnType.DEBIT);
		}
		userAccount.setTraxnStatus(TraxnStatus.SUCCESS);
		return userAccountRepository.save(userAccount);
	}

	@Override
	public UserAccount withdrawAmount(Long accountNumber, Double amount) {
		UserAccount userAccount = userAccountRepository.findOne(accountNumber);
		if (userAccount != null) {
			Double availableBalance = userAccount.getAvailableBalance() - amount;
			if (availableBalance >= MINIMUM_BALANCE) {
				userAccount = UpdateAccount(userAccount, amount, availableBalance, TraxnType.DEBIT);
			} else {
				userAccount.setTraxnStatus(TraxnStatus.INSUFFICIENT_BALANCE);
				userAccountRepository.save(userAccount);
			}

		}
		return userAccount;
	}

	@Override
	public UserAccount getBalance(Long accountNumber) {
		UserAccount userAccount = userAccountRepository.findOne(accountNumber);
		return userAccount;
	}

	@Override
	public Customer getCustomerDetail(UserAccount userAccount) {
		return customerRepository.findByAccountNumber(userAccount.getAccountNumber());

	}

}
