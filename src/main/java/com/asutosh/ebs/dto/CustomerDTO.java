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

	private Long customerId;

	private String customerName;

	private String mobileNumber;

	private String gender;

	private Set<AddressDTO> addresses;
	
	private Set<UserLoginDTO> userLogin;
	
	
	public CustomerDTO() {}
	
	public CustomerDTO(Customer customer) {
		this(customer.getCustomerId(), 
				customer.getCustomerName(), 
				customer.getMobileNumber(), 
				customer.getGender());
	}
	
	public CustomerDTO(Long customerId, String customerName, String mobileNumber, String gender) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public Set<UserLoginDTO> getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Set<UserLoginDTO> userLogin) {
		this.userLogin = userLogin;
	}
	
	

}
