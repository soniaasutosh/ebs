package com.asutosh.ebs.rest;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asutosh.ebs.domain.Address;
import com.asutosh.ebs.domain.MetreReading;
import com.asutosh.ebs.domain.UserLogin;
import com.asutosh.ebs.dto.AddressDTO;
import com.asutosh.ebs.dto.BillDTO;
import com.asutosh.ebs.dto.MetreReadingDTO;
import com.asutosh.ebs.dto.UserLoginDTO;
import com.asutosh.ebs.repository.UserLoginRepository;

@RestController
@RequestMapping("/api/userLogin")
public class UserLoginRestController {

	private static final Logger log = LoggerFactory.getLogger(UserLoginRestController.class);

	private UserLoginRepository userLoginRepository;

	
	public UserLoginRestController(UserLoginRepository userLoginRepository) {
		this.userLoginRepository = userLoginRepository;
	}

@GetMapping("/ping")
public ResponseEntity<?> checkHealth() {
	log.info("Called checkHealth");
	try {

		return new ResponseEntity<>("Working OK - " + this.userLoginRepository.count(), HttpStatus.OK);
	} catch (Exception e) {
		log.info("Error in checkHealth " + e.getMessage());
		return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
	}

}
@GetMapping
public ResponseEntity<?> getAll() {
	log.info("Called getAll");
	try {

		return new ResponseEntity<>(
				this.userLoginRepository.findAll().stream().map(UserLoginDTO::new).collect(Collectors.toList()),
				HttpStatus.OK);
	} catch (Exception e) {
		log.info("Error in getAll " + e.getMessage());
		return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
	}
}

@PostMapping
public ResponseEntity<?> createUserLogin(@RequestBody UserLogin userLogin) {
	log.info("Called createUserLogin");
	try {

		return new ResponseEntity<>(this.userLoginRepository.save(userLogin),
				HttpStatus.OK);
	} catch (Exception e) {
		log.info("Error in createUserLogin" + e.getMessage());

		if (e.getMessage().contains("ConstraintViolationException")) {
			return new ResponseEntity<>("Duplicate data", HttpStatus.BAD_REQUEST);
		} else {	
		return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
	}
}
}


@GetMapping("/{userLoginId}")	
public ResponseEntity<?> getUserLogin(@PathVariable Long userLoginId) {
	log.info("Called getUserLogin  "+ userLoginId);
	try {
		Optional<UserLogin> userLogin= this.userLoginRepository.findById(userLoginId);
		if (userLogin.isPresent()) {
			UserLoginDTO userLoginDTO = new UserLoginDTO(userLogin.get());
			 
			return new ResponseEntity<>(userLoginDTO, HttpStatus.OK);
		} else {
			throw new Exception("UserLogin not found");
		}

		 
	} catch (Exception e) {
		log.info("Error in createUserLogin" + e.getMessage());
		if (e.getMessage().contains("UserLogin not found")) {
			return new ResponseEntity<>("UserLogin not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}

@PutMapping("/{userLoginId}")	
public ResponseEntity<?> updateUserLogin(@PathVariable Long userLoginId,@RequestBody UserLogin requestUserLogin) {
	log.info("Called updateUserLogin "+ userLoginId);
	try {
		Optional<UserLogin>userLogin = this.userLoginRepository.findById(userLoginId);
		if (userLogin.isPresent()) {
			
			userLogin.get().setUsername(requestUserLogin.getUsername());
			userLogin.get().setPassword(requestUserLogin.getPassword());
			userLogin.get().setRole(requestUserLogin.getRole());
			
			UserLoginDTO userLoginDTO = new UserLoginDTO(this.userLoginRepository.save(userLogin.get()));
			 
			return new ResponseEntity<>(userLoginDTO, HttpStatus.OK);
		} else {
			throw new Exception("UserLogin not found");
		}

		 
	} catch (Exception e) {
		log.error("Error in createUserLogin" + e.getMessage());
		if (e.getMessage().contains("UserLogin not found")) {
			return new ResponseEntity<>("UserLogin not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}

}

@DeleteMapping("/{userLoginId}")	
public ResponseEntity<?> deleteUserLogin(@PathVariable Long userLoginId) {
	log.info("Called deleteeUserLogin  "+ userLoginId);
	try {
		Optional<UserLogin> userLogin= this.userLoginRepository.findById(userLoginId);
		if (userLogin.isPresent()) {
			
			
			this.userLoginRepository.delete(userLogin.get());
			 
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		} else {
			throw new Exception("UserLogin not found");
		}

		 
	} catch (Exception e) {
		log.error("Error in deleteUserLogin" + e.getMessage());
		if (e.getMessage().contains("UserLogin not found")) {
			return new ResponseEntity<>("UserLogin not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}

}