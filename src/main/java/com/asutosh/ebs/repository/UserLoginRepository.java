package com.asutosh.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asutosh.ebs.domain.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin , Long>{

}
