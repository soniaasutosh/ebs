package com.asutosh.ebs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "payment")
public class Payment {
	
	public enum  PaymentMethods
	{
	    CARD, UPI, WALLET;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Column(name = "payment_amount")
	private Long paymentAmount;
	
	@Column(name = "payment_mode")
	private PaymentMethods paymentMode;
 
	@Column(name = "created_on")
	private Date createdOn;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public PaymentMethods getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMethods paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	

	 
}
