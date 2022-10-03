package com.asutosh.ebs.dto;

import java.io.Serializable;
import java.util.Date;

import com.asutosh.ebs.domain.MetreReading;
import com.asutosh.ebs.domain.Payment;
import com.asutosh.ebs.domain.Payment.PaymentMethods;

public class PaymentDTO {

	private Long paymentId;

	private Long paymentAmount;

	private PaymentMethods paymentMode;

	private Date createdOn;

	public Long getPaymentId() {
		return paymentId;
	}
	
	
	 public PaymentDTO() {}
		
		public PaymentDTO(Payment payment) {
			this(payment.getPaymentId(),
					payment.getPaymentAmount(),
					payment.getPaymentMode(),
					payment.getCreatedOn()
					);
		}
	public PaymentDTO(Long paymentId, Long paymentAmount, PaymentMethods paymentMode, Date createdOn) {
			super();
			this.paymentId = paymentId;
			this.paymentAmount = paymentAmount;
			this.paymentMode = paymentMode;
			this.createdOn = createdOn;
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
