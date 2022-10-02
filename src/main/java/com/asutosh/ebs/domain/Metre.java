package com.asutosh.ebs.domain;

import java.io.Serializable;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "Metre")
public class Metre implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "metre_id")
	private Long metreId;

	@Column(name = "metre_number")
	private String metreNumber;

	@Column(name = "status")
	private String status;

	@Column(name = "installation_date")
	private String installationDate;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id", nullable=true)
	private Address address;
	
	@Transient
	private Long addressId;

      
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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	public Stream<Bill> stream() {
		// TODO Auto-generated method stub
		return null;
	}

	 
}
