package com.keypot.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keypot.app.entity.UserAccount.TraxnStatus;
import com.keypot.app.entity.UserAccount.TraxnType;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AccountDto implements Serializable {

	private Double debitedAmount;
	private Double creditedAmount;
	private Double availableBalance;
	private Long accountNumber;
	private TraxnStatus traxnStatus;
	private TraxnType traxnType;

	public Double getDebitedAmount() {
		return debitedAmount;
	}

	@Override
	public String toString() {
		return "AccountDto [debitedAmount=" + debitedAmount + ", creditedAmount=" + creditedAmount
				+ ", availableBalance=" + availableBalance + ", accountNumber=" + accountNumber + ", traxnStatus="
				+ traxnStatus + ", traxnType=" + traxnType + "]";
	}

	public void setDebitedAmount(Double debitedAmount) {
		this.debitedAmount = debitedAmount;
	}

	public Double getCreditedAmount() {
		return creditedAmount;
	}

	public void setCreditedAmount(Double creditedAmount) {
		this.creditedAmount = creditedAmount;
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

	public TraxnStatus getTraxnStatus() {
		return traxnStatus;
	}

	public void setTraxnStatus(TraxnStatus traxnStatus) {
		this.traxnStatus = traxnStatus;
	}

	public TraxnType getTraxnType() {
		return traxnType;
	}

	public void setTraxnType(TraxnType traxnType) {
		this.traxnType = traxnType;
	}

}
