package com.asutosh.ebs.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.asutosh.ebs.domain.Metre;
import com.asutosh.ebs.domain.UserLogin;

public class UserLoginDTO {

	private Long userLoginId;

	private String username;

	private String password;

	private String role;

	private CustomerDTO customer;
	
	private Long customerId;

   public UserLoginDTO() {}
   
    public UserLoginDTO(UserLogin userLogin) {
    	this(userLogin.getUserLoginId(), 
    			userLogin.getUsername(),
    			userLogin.getPassword(),
    			userLogin.getRole(),
    			userLogin.getCustomer()!= null ? userLogin.getCustomer().getCustomerId() : null
				);
	}

	
	
	public UserLoginDTO(Long userLoginId, String username, String password, String role,
			Long customerId) {
		super();
		this.userLoginId = userLoginId;
		this.username = username;
		this.password = password;
		this.role = role;
		this.customerId = customerId;
	}



	 

	public Long getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(Long userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}