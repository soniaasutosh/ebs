package com.asutosh.ebs.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "Bill")
public class Bill implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private Long billId;

	@Column(name = "bill_date")
	private Date billDate;

	@Column(name = "due_date")
	private String dueDate;

	@Column(name = "bill_amount")
	private String billAmount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "paid_amount")
	private String paidAmount;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="metre_id", nullable=true)
	private Metre metre;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_id", nullable=true)
	private Payment payment;
	
	@Transient
	private Long metreId;
	
	@Transient
	private Long paymentId;
	
	public Long bill_id() {
		return billId;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Metre getMetre() {
		return metre;
	}

	public void setMetre(Metre metre) {
		this.metre = metre;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Long getMetreId() {
		return metreId;
	}

	public void setMetreId(Long metreId) {
		this.metreId = metreId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Object getBill() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Bill get() {
		// TODO Auto-generated method stub
		return null;
	}
}