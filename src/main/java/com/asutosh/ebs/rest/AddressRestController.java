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

	@PostMapping
	public ResponseEntity<?> createAddress(@RequestBody Address address) {
		log.info("Called createAddress");
		try {

			return new ResponseEntity<>(this.addressRepositoy.save(address),
					HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in createAddress" + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/{addressId}")	
	public ResponseEntity<?> getAddress(@PathVariable Long addressId) {
		log.info("Called getAddress  "+ addressId);
		try {
			Optional<Address> address = this.addressRepositoy.findById(addressId);
			if (address.isPresent()) {
				AddressDTO addressDTO = new AddressDTO(address.get());
				 
				return new ResponseEntity<>(addressDTO, HttpStatus.OK);
			} else {
				throw new Exception("Customer not found");
			}

			 
		} catch (Exception e) {
			log.info("Error in createAddress" + e.getMessage());
			if (e.getMessage().contains("Customer not found")) {
				return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	
	@PutMapping("/{addressId}")	
	public ResponseEntity<?> updateAddress(@PathVariable Long addressId,@RequestBody Address requestAddress) {
		log.info("Called updateAddress  "+ addressId);
		try {
			Optional<Address> address = this.addressRepositoy.findById(addressId);
			if (address.isPresent()) {
				
				address.get().setCity(requestAddress.getCity());
				address.get().setFullAddress(requestAddress.getFullAddress());
				address.get().setState(requestAddress.getState());
				address.get().setPostal(requestAddress.getPostal());
				AddressDTO addressDTO = new AddressDTO(this.addressRepositoy.save(address.get()));
				 
				return new ResponseEntity<>(addressDTO, HttpStatus.OK);
			} else {
				throw new Exception("Customer not found");
			}

			 
		} catch (Exception e) {
			log.error("Error in createAddress" + e.getMessage());
			if (e.getMessage().contains("Customer not found")) {
				return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@DeleteMapping("/{addressId}")	
	public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
		log.info("Called deleteeAddress  "+ addressId);
		try {
			Optional<Address> address = this.addressRepositoy.findById(addressId);
			if (address.isPresent()) {
				
				
				this.addressRepositoy.delete(address.get());
				 
				return new ResponseEntity<>("deleted", HttpStatus.OK);
			} else {
				throw new Exception("Customer not found");
			}

			 
		} catch (Exception e) {
			log.error("Error in deleteAddress" + e.getMessage());
			if (e.getMessage().contains("Customer not found")) {
				return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	
}
