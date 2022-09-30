package com.asutosh.ebs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "MetreReading")
public class MetreReading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "metre_reading_id")
	private Long metre_reading_id;

	@Column(name = "current_reading")
	private  String currentReading;

	public Long getMetre_reading_id() {
		return metre_reading_id;
	}

	public void setMetre_reading_id(Long metre_reading_id) {
		this.metre_reading_id = metre_reading_id;
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

	@Column(name = "created_on")
	private String createdOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="metre_id", nullable=false)
	private Metre metre;

	public Long metre_reading_id() {
		return metre_reading_id;
	}

}