package com.asutosh.ebs.dto;

public class MetreDTO {

	private Long metreId;

	private String metreNumber;

	private String status;

	private String installationDate;

	private AddressDTO address;

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

}
