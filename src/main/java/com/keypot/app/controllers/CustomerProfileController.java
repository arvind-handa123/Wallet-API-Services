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
		CustomerDto customerDto = new CustomerDto();
		Long accountNumber = customerService.createAccount(customerName, email);
		if (accountNumber != null) {
			customerDto.setAccountNumber(accountNumber);
			customerDto.setIsAccountCreated(true);

		} else {
			customerDto.setIsAccountCreated(false);
			customerDto.setRemark("Given email id alraeady exist!");
			return new ResponseEntity<>(customerDto,HttpStatus.IM_USED);
		}
		return new ResponseEntity<>(customerDto, HttpStatus.OK);
	}

}
