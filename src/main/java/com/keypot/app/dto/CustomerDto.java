package com.keypot.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CustomerDto implements Serializable {

	private Long accountNumber;
	private String accountHolderName;
	private String email;
	private Double availableBalance;

	@Override
	public String toString() {
		return "CustomerDto [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName + ", email="
				+ email + ", availableBalance=" + availableBalance + "]";
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
