package com.asutosh.ebs.dto;

import javax.persistence.Transient;

import com.asutosh.ebs.domain.Metre;

public class MetreDTO {

	private Long metreId;

	private String metreNumber;

	private String status;

	private String installationDate;

	private AddressDTO address;
	
	private Long addressId;

	
	public MetreDTO() {}
	
	public MetreDTO(Metre metre) {
		this(metre.getMetreId(), 
				metre.getMetreNumber(),
				metre.getStatus(),
				metre.getInstallationDate(),
				metre.getAddress()!=null ? metre.getAddress().getAddressId() : null
				);
	}
	

	public MetreDTO(Long metreId, String metreNumber, String status, String installationDate, Long addressId) {
		super();
		this.metreId = metreId;
		this.metreNumber = metreNumber;
		this.status = status;
		this.installationDate = installationDate;
		this.addressId = addressId;
	}



	public Long getMetreId() {
		return metreId;
	}

	public void setMetreId(Long metreId) {
		this.metreId = metreId;
	}

	public String getMetreNumber() {
		return metreNumber;
	}

	public void setMetreNumber(String metreNumber) {
		this.metreNumber = metreNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(String installationDate) {
		this.installationDate = installationDate;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

}
