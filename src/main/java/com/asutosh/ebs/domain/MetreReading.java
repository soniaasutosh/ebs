package com.asutosh.ebs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "MetreReading")
public class MetreReading implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "metre_reading_id")
	private Long metreReadingId;

	@Column(name = "current_reading")
	private  String currentReading;
	
 	@Column(name = "created_on")
	private String createdOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="metre_id", nullable=true)
	private Metre metre;
	
	@Transient
	private Long metreId;

	public Long getMetreReadingId() {
		return metreReadingId;
	}

	public void setMetreReadingId(Long metreReadingId) {
		this.metreReadingId = metreReadingId;
	}

	public String getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(String currentReading) {
		this.currentReading = currentReading;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public Metre getMetre() {
		return metre;
	}

	public void setMetre(Metre metre) {
		this.metre = metre;
	}

	public Long getMetreId() {
		return metreId;
	}

	public void setMetreId(Long metreId) {
		this.metreId = metreId;
	}


}