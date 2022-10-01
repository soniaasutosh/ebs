package com.asutosh.ebs.dto;

import java.io.Serializable;

import com.asutosh.ebs.domain.Address;

public class AddressDTO implements Serializable {

	private Long addressId;

	private String fullAddress;

	private String city;

	private String state;

	private String postal;

	private CustomerDTO customer;

	private Long cutomerId;
	
	public AddressDTO() {}
    
	public AddressDTO(Address address) {
	
		this(	address.getAddressId(), 
				address.getFullAddress(), 
				address.getCity(), 
				address.getState(), 
				address.getPostal(), 
				address.getCustomer()!=null?address.getCustomer().getCutomerId():null);
	}
	

	public AddressDTO(Long addressId, String fullAddress, String city, String state, String postal, Long cutomerId) {
		super();
		this.addressId = addressId;
		this.fullAddress = fullAddress;
		this.city = city;
		this.state = state;
		this.postal = postal;
		this.cutomerId = cutomerId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public Long getCutomerId() {
		return cutomerId;
	}

	public void setCutomerId(Long cutomerId) {
		this.cutomerId = cutomerId;
	}

}
