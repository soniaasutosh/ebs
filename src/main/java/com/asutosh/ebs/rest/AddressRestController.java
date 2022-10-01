package com.asutosh.ebs.rest;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asutosh.ebs.dto.AddressDTO;
import com.asutosh.ebs.repository.AddressRepositoy;

@RestController
@RequestMapping("/address")
public class AddressRestController {

	private static final Logger log = LoggerFactory.getLogger(AddressRestController.class);

	private AddressRepositoy addressRepositoy;

	public AddressRestController(AddressRepositoy addressRepositoy) {
		this.addressRepositoy = addressRepositoy;
	}

	@GetMapping("/ping")
	public ResponseEntity<?> checkHealth() {
		log.info("Called checkHealth");
		try {

			return new ResponseEntity<>("Working OK - " + this.addressRepositoy.count(), HttpStatus.OK);
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
					this.addressRepositoy.findAll().stream().map(AddressDTO::new).collect(Collectors.toList()),
					HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in getAll " + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}
