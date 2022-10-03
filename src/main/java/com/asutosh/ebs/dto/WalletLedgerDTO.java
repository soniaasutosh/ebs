package com.asutosh.ebs.dto;

import com.asutosh.ebs.domain.UserLogin;
import com.asutosh.ebs.domain.WalletLedger;
import com.asutosh.ebs.domain.WalletLedger.TransactionType;

public class WalletLedgerDTO {

	private Long walletLedgerId;

	private long currentBalance;

	private long amount;

	private TransactionType transactionType;

	private PaymentDTO payment;
	
	private Long paymentId;
	
	public WalletLedgerDTO(WalletLedger walletLedger) {
    	this(walletLedger.getWalletLedgerId(), 
    			walletLedger.getAmount(),
    			walletLedger.getCurrentBalance(),
    			walletLedger.getTransactionType(),
    			walletLedger.getPayment()!= null ? walletLedger.getPayment().getPaymentId() : null
				);
	
	
	}


	public WalletLedgerDTO(Long walletLedgerId, long currentBalance, long amount, TransactionType transactionType,
			 Long paymentId) {
		super();
		this.walletLedgerId = walletLedgerId;
		this.currentBalance = currentBalance;
		this.amount = amount;
		this.transactionType = transactionType;
		
		this.paymentId = paymentId;
	}
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

	public PaymentDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
}
