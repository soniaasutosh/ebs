package com.asutosh.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asutosh.ebs.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment , Long>{

}

