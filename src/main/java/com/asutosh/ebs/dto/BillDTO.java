package com.asutosh.ebs.dto;

import java.io.Serializable;
import java.util.Date;

import com.asutosh.ebs.domain.Bill;
import com.asutosh.ebs.domain.Metre;

public class BillDTO implements Serializable {

	private Long billId;

	private Date billDate;

	private String dueDate;

	private String billAmount;

	private String status;

	private String paidAmount;

	private Date createdOn;

	private MetreDTO metre;
	
	private Long metreId;

	private PaymentDTO payment;
	
	private Long paymentId;
	

	
//create
	public BillDTO() {}
	
	public BillDTO(Bill bill) {
		this(bill.getBillId(), 
				bill.getBillDate(),
				bill.getDueDate(),
				bill.getBillAmount(), 
				bill.getStatus(),
				bill.getPaidAmount(),
				bill.getCreatedOn(),
				bill.getMetre()!=null ? bill.getMetre().getMetreId() : null,
			    bill.getPayment()!=null ? bill.getPayment().getPaymentId() : null
			    );		
	}
	

	public BillDTO(Long billId, Date billDate, String dueDate, String billAmount, String status, String paidAmount,
			Date createdOn, Long metreId, Long paymentId) {
		super();
		this.billId = billId;
		this.billDate = billDate;
		this.dueDate = dueDate;
		this.billAmount = billAmount;
		this.status = status;
		this.paidAmount = paidAmount;
		this.createdOn = createdOn;
		this.metreId = metreId;
		this.paymentId = paymentId;
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

	public MetreDTO getMetre() {
		return metre;
	}

	public void setMetre(MetreDTO metre) {
		this.metre = metre;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDTO payment) {
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

}