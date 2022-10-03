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
import com.asutosh.ebs.domain.Metre;
import com.asutosh.ebs.domain.MetreReading;
import com.asutosh.ebs.domain.Payment;
import com.asutosh.ebs.dto.AddressDTO;
import com.asutosh.ebs.dto.MetreReadingDTO;
import com.asutosh.ebs.dto.PaymentDTO;
import com.asutosh.ebs.repository.AddressRepositoy;
import com.asutosh.ebs.repository.PaymentRepository;

@RestController
@RequestMapping("/payment")
public class PaymentRestController {

	private static final Logger log = LoggerFactory.getLogger(PaymentRestController.class);

	private PaymentRepository paymentRepository;

	public PaymentRestController(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@GetMapping("/ping")
	public ResponseEntity<?> checkHealth() {
		log.info("Called checkHealth");
		try {

			return new ResponseEntity<>("Working OK - " + this.paymentRepository.count(), HttpStatus.OK);
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
					this.paymentRepository.findAll().stream().map(PaymentDTO::new).collect(Collectors.toList()),
					HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in getAll " + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
}
	@PostMapping
	public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
		log.info("Called createPayment");
		try {
			return new ResponseEntity<>(this.paymentRepository.save(payment), HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in createMetre" + e.getMessage());
			return new ResponseEntity<>("not working", HttpStatus.BAD_REQUEST);
		}

	
}

	@GetMapping("/{paymentId}")	
	public ResponseEntity<?> getPayment(@PathVariable Long paymentId) {
		log.info("Called getPayment "+ paymentId);
		try {
			Optional<Payment> payment = this.paymentRepository.findById(paymentId);
			if (payment.isPresent()) {
				PaymentDTO paymentDTO = new PaymentDTO(payment.get());
				 
				return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
			} else {
				throw new Exception("payment not found");
			}

			 
		} catch (Exception e) {
			log.info("Error in createpayment" + e.getMessage());
			if (e.getMessage().contains("Payment not found")) {
				return new ResponseEntity<>("Payment not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
}

	@PutMapping("/{paymentId}")	
	public ResponseEntity<?> updatePayment(@PathVariable Long paymentId,@RequestBody Payment requestPayment) {
		log.info("Called updatePayment"+ paymentId);
		try {
			Optional<Payment> payment = this.paymentRepository.findById(paymentId);
			if (payment.isPresent()) {
				
				payment.get().setPaymentAmount(requestPayment.getPaymentAmount());
				payment.get().setPaymentMode(requestPayment.getPaymentMode());
				payment.get().setCreatedOn(requestPayment.getCreatedOn());
				PaymentDTO paymentDTO = new PaymentDTO(this.paymentRepository.save(payment.get()));
				 
				return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
			} else {
				throw new Exception("Payment not found");
			}

			 
		} catch (Exception e) {
			log.error("Error in createPayment" + e.getMessage());
			if (e.getMessage().contains("Payment not found")) {
				return new ResponseEntity<>("Payment not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	
}
	@DeleteMapping("/{paymentId}")	
	public ResponseEntity<?> deletePayment(@PathVariable Long paymentId) {
		log.info("Called deletePayment  "+ paymentId);
		try {
			Optional<Payment> payment= this.paymentRepository.findById(paymentId);
			if (payment.isPresent()) {
				
				
				this.paymentRepository.delete(payment.get());
				 
				return new ResponseEntity<>("deleted", HttpStatus.OK);
			} else {
				throw new Exception("Payment not found");
			}

			 
		} catch (Exception e) {
			log.error("Error in deleteAddress" + e.getMessage());
			if (e.getMessage().contains("Payment not found")) {
				return new ResponseEntity<>(" Payment not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	
}


