package com.asutosh.ebs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Metre")
public class Metre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "metre_id")
	private Long metre_id;

	@Column(name = "metre_number")
	private String metre_number;

	@Column(name = "status")
	private String status;

	@Column(name = "installation_date")
	private String installation_date;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id", nullable=false)
	private Address address;

	public Long metre_id() {
		return metre_id;
	}

	public Long getMetre_id() {
		return metre_id;
	}

	public void setMetre_id(Long metre_id) {
		this.metre_id = metre_id;
	}

	public String getMetre_number() {
		return metre_number;
	}

	public void setMetre_number(String metre_number) {
		this.metre_number = metre_number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstallation_date() {
		return installation_date;
	}

	public void setInstallation_date(String installation_date) {
		this.installation_date = installation_date;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
