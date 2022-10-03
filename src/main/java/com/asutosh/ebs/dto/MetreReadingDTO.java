package com.asutosh.ebs.dto;

import javax.persistence.Transient;

import com.asutosh.ebs.domain.Metre;
import com.asutosh.ebs.domain.MetreReading;

public class MetreReadingDTO {

	private Long metreReadingId;
	
	private String createdOn;
	
	private  String currentReading;

	private MetreDTO metre;
	
	private Long metreId;
	


    public MetreReadingDTO() {}
	
	public MetreReadingDTO(MetreReading metreReading) {
		this(metreReading.getMetreReadingId(), 
				metreReading.getCreatedOn(),
				metreReading.getCurrentReading(),
				metreReading.getMetre()!=null ? metreReading.getMetre().getMetreId() : null
				);
	}
	    
	public MetreReadingDTO(Long metreReadingId, String createdOn, String currentReading, Long metreId) {
		super();
		this.metreReadingId = metreReadingId;
		this.createdOn = createdOn;
		this.currentReading = currentReading;
		this.metreId = metreId;
	}

	public Long getMetreReadingId() {
		return metreReadingId;
	}

	public void setMetreReadingId(Long metreReadingId) {
		this.metreReadingId = metreReadingId;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(String currentReading) {
		this.currentReading = currentReading;
	}

	public MetreDTO getMetre() {
		return metre;
	}

	public void setMetre(MetreDTO metre) {
		this.metre = metre;
	}

	public Long getMetreId() {
		return metreId;
	}

	public void setMetreId(Long metreId) {
		this.metreId = metreId;
	}

}