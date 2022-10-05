package com.asutosh.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asutosh.ebs.domain.Address;

public interface AddressRepositoy extends JpaRepository<Address, Long>  {

}
