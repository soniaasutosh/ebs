package com.asutosh.ebs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "WalletLedger")
public class WalletLedger implements Serializable{
	
	public enum  TransactionType
	{
	    CREDIT,DEBIT;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wallet_ledger_id")
	private Long walletLedgerId;

	@Column(name = "current_balance")
	private long currentBalance;

	@Column(name = "amount")
	private long amount;


	@Column(name = "transactio_type",columnDefinition = "ENUM('CREDIT', 'DEBIT')")
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_id", nullable=false)
	private Payment payment;

	public Long getWalletLedgerId() {
		return walletLedgerId;
	}

	public void setWalletLedgerId(Long walletLedgerId) {
		this.walletLedgerId = walletLedgerId;
	}

	public long getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(long currentBalance) {
		this.currentBalance = currentBalance;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}