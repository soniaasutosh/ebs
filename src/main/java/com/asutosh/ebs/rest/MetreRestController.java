package com.asutosh.ebs.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.asutosh.ebs.domain.Customer;
import com.asutosh.ebs.domain.Metre;
import com.asutosh.ebs.dto.AddressDTO;
import com.asutosh.ebs.dto.MetreDTO;
import com.asutosh.ebs.repository.MetreRepository;

@RestController
@RequestMapping("/metre")
public class MetreRestController {

	private static final Logger log = LoggerFactory.getLogger(MetreRestController.class);

	@Autowired
	private MetreRepository MetreRepository;

	private Object metreRepository;

	private Object get;


	@GetMapping("/ping")
	public ResponseEntity<?> ping() {
		log.info("Called checkHealth");
		try {

			return new ResponseEntity<>("Working OK - " + this.MetreRepository.count(), HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in checkHealth " + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		log.info("Called getAll");
		try {

			return new ResponseEntity<>("Working OK - " + this.MetreRepository.count(), HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in checkHealth " + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<?> createMetre(@RequestBody Metre metre) {
		log.info("Called createMetre");
		try {
			return new ResponseEntity<>(this.MetreRepository.save(metre), HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in createMetre" + e.getMessage());
			return new ResponseEntity<>("not working", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/{metreId}")
      public  ResponseEntity<?> getMetre(@PathVariable Long metreId) {
	log.info("Called getMetre  "+ metreId);
	try {
		Optional<Metre> metre = this.MetreRepository.findById(metreId);
		if (metre.isPresent()) {
			MetreDTO metreDTO = new MetreDTO(metre.get());
			 
			return new ResponseEntity<>(metreDTO, HttpStatus.OK);
		} else {
			throw new Exception("metre not found");
		}
		}catch (Exception e) {
				log.info("Error in getMetre " + metreId + "  ::  " + e.getMessage());
		
				if (e.getMessage().contains("metre not found")) {
					return new ResponseEntity<>("metre not found", HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
				}
		
		}
}		
   @PutMapping("/{metreId}")	
		public ResponseEntity<?> updateMetre(@PathVariable Long metreId,@RequestBody Metre requestMetre) {
			log.info("Called updateMetre"+ metreId);
			try {
				Optional<Metre> metre = this.MetreRepository.findById(metreId);
				if (metre.isPresent()) {
					
					metre.get().setInstallationDate(requestMetre.getInstallationDate());
					metre.get().setMetreNumber(requestMetre.getMetreNumber());
					metre.get().setStatus(requestMetre.getStatus());
					
					MetreDTO metreDTO = new MetreDTO(this.MetreRepository.save(metre.get()));
					 
					return new ResponseEntity<>(metreDTO, HttpStatus.OK);
				} else {
					throw new Exception("metre not found");
				}

				 
			} catch (Exception e) {
				log.error("Error in createMetre" + e.getMessage());
				if (e.getMessage().contains("Metre not found")) {
					return new ResponseEntity<>("Metre not found", HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
				}
			}
		}	

		 

	@DeleteMapping("/{metreId}")
	public ResponseEntity<?> metreCustomer(@PathVariable Long metreId) {
		log.info("Called metreId" + metreId);
		try {
			Optional<Metre> metre = this.MetreRepository.findById(metreId );

			if (metre.isPresent()) {
				this.MetreRepository.delete(metre.get());
				return new ResponseEntity<>("Deleted", HttpStatus.OK);
			} else {
				throw new Exception("Metre not found");
			}
		} catch (Exception e) {
			log.info("Error in getMetre " + metreId + "  ::  " + e.getMessage());

			if (e.getMessage().contains("metre not found")) {
				return new ResponseEntity<>("Metre not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}

}

