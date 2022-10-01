package com.asutosh.ebs.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import com.asutosh.ebs.domain.Customer;

public class CustomerDTO implements Serializable {

	private Long cutomerId;

	private String customerName;

	private String mobileNumber;

	private String gender;

	private Set<AddressDTO> addresses;
	
	public CustomerDTO() {}
	
	public CustomerDTO(Customer customer) {
		this(customer.getCutomerId(), 
				customer.getCustomerName(), 
				customer.getMobileNumber(), 
				customer.getGender());
	}
	
	public CustomerDTO(Long cutomerId, String customerName, String mobileNumber, String gender) {
		super();
		this.cutomerId = cutomerId;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
	}

	public Long getCutomerId() {
		return cutomerId;
	}

	public void setCutomerId(Long cutomerId) {
		this.cutomerId = cutomerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	
	

}
