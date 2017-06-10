package com.keypot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keypot.app.dto.CustomerDto;
import com.keypot.app.service.CustomerService;

@Controller
@RequestMapping(value = "/v1")
public class CustomerProfileController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/create/account", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@RequestParam(name = "customerName") String customerName,
			@RequestParam(name = "email") String email) {
		Long accountNumber = customerService.createAccount(customerName, email);
		CustomerDto customerDto = new CustomerDto();
		customerDto.setAccountNumber(accountNumber);
		return new ResponseEntity<>(customerDto,HttpStatus.OK);
	}

}
