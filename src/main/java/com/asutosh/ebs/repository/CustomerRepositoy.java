package com.asutosh.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asutosh.ebs.domain.Customer;

public interface CustomerRepositoy extends JpaRepository<Customer, Long>  {

}
