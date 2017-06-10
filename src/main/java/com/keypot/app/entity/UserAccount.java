package com.keypot.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 
 * @author Asad Ali
 *
 */
@Entity
public class UserAccount {
	public enum TraxnType {
		DEBIT, CREDIT, BALANCE_ENQUIRY, MINI_STATEMENT;
	}

	public enum TraxnStatus {
		SUCCESS, FAIL, INSUFFICIENT_BALANCE;
	}

	@Id
	private Long accountNumber;
	private Date createdTime;
	private Date updatedTime;



	private Double debitedAmount;
	private Double creditedAmount;
	private Double availableBalance;
	

	public Double getDebitedAmount() {
		return debitedAmount;
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

	public TraxnType getTraxnType() {
		return traxnType;
	}

	public void setTraxnType(TraxnType traxnType) {
		this.traxnType = traxnType;
	}

	private TraxnType traxnType;
	private TraxnStatus traxnStatus;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public TraxnStatus getTraxnStatus() {
		return traxnStatus;
	}

	public void setTraxnStatus(TraxnStatus traxnStatus) {
		this.traxnStatus = traxnStatus;
	}

	@PrePersist
	protected void updateDates() {
		if (createdTime == null) {
			createdTime = new Date();
			setUpdatedTime(new Date());
		}
	}

	@PreUpdate
	protected void updateDate() {
		setUpdatedTime(new Date());
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
