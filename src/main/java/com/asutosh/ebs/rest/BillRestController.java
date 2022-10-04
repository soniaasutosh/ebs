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
import com.asutosh.ebs.domain.Bill;
import com.asutosh.ebs.domain.Customer;
import com.asutosh.ebs.domain.Metre;
import com.asutosh.ebs.domain.Payment.PaymentMethods;
import com.asutosh.ebs.dto.AddressDTO;
import com.asutosh.ebs.dto.BillDTO;
import com.asutosh.ebs.dto.CustomerDTO;
import com.asutosh.ebs.dto.MetreDTO;
import com.asutosh.ebs.dto.PaymentDTO;
import com.asutosh.ebs.repository.AddressRepositoy;
import com.asutosh.ebs.repository.BillRepository;

@RestController
@RequestMapping("/api/bill")
public class BillRestController {

	private static final Logger log = LoggerFactory.getLogger(BillRestController.class);

	private BillRepository billRepository;

	private Bill updateBill;

	public BillRestController(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	public static Logger getLog() {
		return log;
	}

@GetMapping("/ping")
public ResponseEntity<?> checkHealth() {
	log.info("Called checkHealth");
	try {

		return new ResponseEntity<>("Working OK - " + this.billRepository.count(), HttpStatus.OK);
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
				this.billRepository.findAll().stream().map(BillDTO::new).collect(Collectors.toList()),
				HttpStatus.OK);
	} catch (Exception e) {
		log.info("Error in getAll " + e.getMessage());
		return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
	}
}
@PostMapping
public ResponseEntity<?> createBill(@RequestBody Bill bill) {
	log.info("Called createBill");
	try {

		return new ResponseEntity<>(this.billRepository.save(bill),
				HttpStatus.OK);
	} catch (Exception e) {
		log.info("Error in create " + e.getMessage());

		if (e.getMessage().contains("ConstraintViolationException")) {
			return new ResponseEntity<>("Duplicate data", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}

}
@GetMapping("/{billId}")
public  ResponseEntity<?> getMetre(@PathVariable Long billId) {
log.info("Called getbill "+ billId);
try {
	Optional<Bill> bill = this.billRepository.findById(billId);
	if (bill.isPresent()) {
		BillDTO billDTO = new BillDTO(bill.get());
		 
		return new ResponseEntity<>(billDTO, HttpStatus.OK);
	} else {
		throw new Exception("bill not found");
	}
	}catch (Exception e) {
			log.info("Error in getBill " + billId + "  ::  " + e.getMessage());
	
			if (e.getMessage().contains("bill not found")) {
				return new ResponseEntity<>("bill not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
	
	}
	}


@PutMapping("/{billId}")
public ResponseEntity<?> update(@PathVariable Long billId, @RequestBody Bill updateBill) {
	log.info("Called billId " +billId);
	try {
		Optional<Bill> bill = this.billRepository.findById(billId);

		if (bill.isPresent()) {
			bill.get().setBillDate(updateBill.getBillDate());
			bill.get().setDueDate(updateBill.getDueDate());
			bill.get().setBillAmount(updateBill.getBillAmount());
			bill.get().setStatus(updateBill.getStatus());
			bill.get().setPaidAmount(updateBill.getPaidAmount());
			bill.get().setCreatedOn(updateBill.getCreatedOn());
			return new ResponseEntity<>(this.billRepository.save(bill.get()), HttpStatus.OK);
		} else {
			throw new Exception("Bill not found");
		}
	} catch (Exception e) {
		log.info("Error in getBill " + billId + "  ::  " + e.getMessage());

		if (e.getMessage().contains("Bill not found")) {
			return new ResponseEntity<>("Bill not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}

@DeleteMapping("/{billId}")
public ResponseEntity<?> billCustomer(@PathVariable Long billId) {
	log.info("Called billId" + billId);
	try {
		Optional<Bill> bill = this.billRepository.findById(billId );

		if (bill.isPresent()) {
			this.billRepository.delete(bill.get());
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} else {
			throw new Exception("bill not found");
		}
	} catch (Exception e) {
		log.info("Error in getBill " + billId + "  ::  " + e.getMessage());

		if (e.getMessage().contains("bill not found")) {
			return new ResponseEntity<>("bill not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}

}