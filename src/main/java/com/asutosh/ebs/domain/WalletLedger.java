package com.asutosh.ebs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "WalletLedger")
public class WalletLedger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wallet_ledger_id")
	private Long wallet_ledger_id;

	@Column(name = "current_balance")
	private long currentBalance;

	@Column(name = "amount")
	private long amount;


	@Column(name = "transactio_type")
	private long transactionType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_id", nullable=false)
	private Payment payment;

	public Long Wallet_ledger_id(){
		return Wallet_ledger_id();
	}
		
	public Long getWallet_ledger_id() {
		return wallet_ledger_id;
	}

	public void setWallet_ledger_id(Long wallet_ledger_id) {
		this.wallet_ledger_id = wallet_ledger_id;
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

	public long getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(long transactionType) {
		this.transactionType = transactionType;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}

	
