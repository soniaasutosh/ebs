package com.asutosh.ebs.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asutosh.ebs.repository.MetreRepository;

@RestController
@RequestMapping("/metre")
public class MetreRestController {
	
	private static final Logger log = LoggerFactory.getLogger(MetreRestController.class);
	
	@Autowired
	private MetreRepository metreRestController;

	@GetMapping("/ping")
	public ResponseEntity<?> ping() {
		log.info("Called checkHealth");
		try {

			return new ResponseEntity<>("Working OK - " + this.metreRestController.count(), HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in checkHealth " + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}
