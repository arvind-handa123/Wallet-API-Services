package com.keypot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keypot.app.dto.AccountDto;
import com.keypot.app.dto.CustomerDto;
import com.keypot.app.entity.Customer;
import com.keypot.app.entity.UserAccount;
import com.keypot.app.entity.UserAccount.TraxnStatus;
import com.keypot.app.entity.UserAccount.TraxnType;
import com.keypot.app.service.CustomerService;

@Controller
@RequestMapping(value = "/v1")
public class TransactionController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/deposite", method = RequestMethod.POST)
	public ResponseEntity<?> deposite(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("amount") Double amount) {
		AccountDto accountDto = new AccountDto();
		UserAccount userAccount = customerService.addAmount(accountNumber, amount);
		if (userAccount != null) {
			accountDto.setAccountNumber(accountNumber);
			accountDto.setAvailableBalance(userAccount.getAvailableBalance());
			accountDto.setCreditedAmount(amount);
			accountDto.setTraxnType(TraxnType.CREDIT);
			accountDto.setTraxnStatus(userAccount.getTraxnStatus());
		} else {
			/*
			 * return status in dto saying Account doesn't exists
			 */
			accountDto.setTraxnStatus(TraxnStatus.ACCOUNT_DOESNT_EXIST);
			return new ResponseEntity<>(accountDto, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(accountDto, HttpStatus.OK);

	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public ResponseEntity<?> withdraw(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("amount") Double amount) {
		UserAccount userAccount = customerService.withdrawAmount(accountNumber, amount);
		AccountDto accountDto = new AccountDto();
		if (userAccount != null) {
			if (TraxnStatus.SUCCESS.equals(userAccount.getTraxnStatus())) {
				accountDto.setAccountNumber(accountNumber);
				accountDto.setAvailableBalance(userAccount.getAvailableBalance());
				accountDto.setDebitedAmount(amount);
			} else {
				accountDto.setTraxnType(TraxnType.DEBIT);
				accountDto.setTraxnStatus(userAccount.getTraxnStatus());
			}
		} else {
			/*
			 * return status in dto saying Account doesn't exists
			 */
			accountDto.setTraxnStatus(TraxnStatus.ACCOUNT_DOESNT_EXIST);
			return new ResponseEntity<>(accountDto, HttpStatus.UNAUTHORIZED);

		}
		return new ResponseEntity<>(accountDto, HttpStatus.OK);

	}

	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public ResponseEntity<?> enquiry(@RequestParam("accountNumber") Long accountNumber) {
		Customer customer = null;
		CustomerDto customerDto = new CustomerDto();
		UserAccount userAccount = customerService.getBalance(accountNumber);
		if (userAccount != null) {
			customer = customerService.getCustomerDetail(userAccount);
		} else {
			return new ResponseEntity<>(customerDto, HttpStatus.NOT_FOUND);
		}
		if (customer != null) {
			customerDto.setAccountHolderName(customer.getCustomerName());
			customerDto.setEmail(customer.getEmailId());
			customerDto.setAvailableBalance(userAccount.getAvailableBalance());
			customerDto.setAccountNumber(accountNumber);
		}

		return new ResponseEntity<>(customerDto, HttpStatus.OK);

	}

}
