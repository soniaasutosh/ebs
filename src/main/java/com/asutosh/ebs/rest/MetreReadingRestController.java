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
import com.asutosh.ebs.dto.AddressDTO;
import com.asutosh.ebs.dto.BillDTO;
import com.asutosh.ebs.dto.MetreReadingDTO;
import com.asutosh.ebs.repository.MetreReadingRepository;

@RestController
@RequestMapping("/metreReading")
public class MetreReadingRestController {

	private static final Logger log = LoggerFactory.getLogger(MetreReadingRestController.class);

	private MetreReadingRepository metreReadingRepository;

	
	public MetreReadingRestController(MetreReadingRepository metreReadingRepository) {
		this.metreReadingRepository = metreReadingRepository;
	}

	
	@GetMapping("/ping")
	public ResponseEntity<?> checkHealth() {
		log.info("Called checkHealth");
		try {

			return new ResponseEntity<>("Working OK - " + this.metreReadingRepository.count(), HttpStatus.OK);
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
					this.metreReadingRepository.findAll().stream().map(MetreReadingDTO::new).collect(Collectors.toList()),
					HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in getAll " + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
}
	
	@PostMapping
	public ResponseEntity<?> createMetreReading(@RequestBody MetreReading metreReading) {
		log.info("Called createMetreReading");
		try {

			return new ResponseEntity<>(this.metreReadingRepository.save(metreReading),
					HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in createMetreReading" + e.getMessage());

			if (e.getMessage().contains("ConstraintViolationException")) {
				return new ResponseEntity<>("Duplicate data", HttpStatus.BAD_REQUEST);
			} else {	
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
}
}
	
	@GetMapping("/{metreReadingId}")	
	public ResponseEntity<?> getAddress(@PathVariable Long metreReadingId) {
		log.info("Called getMetreReading  "+ metreReadingId);
		try {
			Optional<MetreReading> metreReading = this.metreReadingRepository.findById(metreReadingId);
			if (metreReading.isPresent()) {
				MetreReadingDTO metreReadingDTO = new MetreReadingDTO(metreReading.get());
				 
				return new ResponseEntity<>(metreReadingDTO, HttpStatus.OK);
			} else {
				throw new Exception("MetreReading not found");
			}

			 
		} catch (Exception e) {
			log.info("Error in createMetreReading" + e.getMessage());
			if (e.getMessage().contains("MetreReading not found")) {
				return new ResponseEntity<>("MetreReading not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
}
	

	@PutMapping("/{metreReadingId}")	
	public ResponseEntity<?> updateMetreReading(@PathVariable Long metreReadingId,@RequestBody MetreReading requestMetreReading) {
		log.info("Called updateMetreReading "+ metreReadingId);
		try {
			Optional<MetreReading> metreReading = this.metreReadingRepository.findById(metreReadingId);
			if (metreReading.isPresent()) {
				
				metreReading.get().setCreatedOn(requestMetreReading.getCreatedOn());
				metreReading.get().setCurrentReading(requestMetreReading.getCurrentReading());
				
				MetreReadingDTO metreReadingDTO = new MetreReadingDTO(this.metreReadingRepository.save(metreReading.get()));
				 
				return new ResponseEntity<>(metreReadingDTO, HttpStatus.OK);
			} else {
				throw new Exception("MetreReading not found");
			}

			 
		} catch (Exception e) {
			log.error("Error in createMetreReading" + e.getMessage());
			if (e.getMessage().contains("MetreReading not found")) {
				return new ResponseEntity<>("MetreReading not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
}
	
	@DeleteMapping("/{metreReadingId}")	
	public ResponseEntity<?> deleteMetreReading(@PathVariable Long metreReadingId) {
		log.info("Called deleteeMetreReading  "+ metreReadingId);
		try {
			Optional<MetreReading> metreReading = this.metreReadingRepository.findById(metreReadingId);
			if (metreReading.isPresent()) {
				
				
				this.metreReadingRepository.delete(metreReading.get());
				 
				return new ResponseEntity<>("deleted", HttpStatus.OK);
			} else {
				throw new Exception("MetreReading not found");
			}

			 
		} catch (Exception e) {
			log.error("Error in deleteMetreReading" + e.getMessage());
			if (e.getMessage().contains("MetreReading not found")) {
				return new ResponseEntity<>("MetreReading not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	
}