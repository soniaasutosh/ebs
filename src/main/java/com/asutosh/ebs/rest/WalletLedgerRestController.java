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
import com.asutosh.ebs.domain.WalletLedger;
import com.asutosh.ebs.dto.AddressDTO;
import com.asutosh.ebs.dto.BillDTO;
import com.asutosh.ebs.dto.MetreReadingDTO;
import com.asutosh.ebs.dto.UserLoginDTO;
import com.asutosh.ebs.dto.WalletLedgerDTO;
import com.asutosh.ebs.repository.UserLoginRepository;
import com.asutosh.ebs.repository.WalletLedgerRepository;

@RestController
@RequestMapping("/walletLedger")
public class WalletLedgerRestController {

	private static final Logger log = LoggerFactory.getLogger(WalletLedgerRestController.class);

	private WalletLedgerRepository walletLedgerRepository;

	
	public WalletLedgerRestController(WalletLedgerRepository walletLedgerRepository) {
		this.walletLedgerRepository =walletLedgerRepository;
	}


@GetMapping("/ping")
public ResponseEntity<?> checkHealth() {
	log.info("Called checkHealth");
	try {

		return new ResponseEntity<>("Working OK - " + this.walletLedgerRepository.count(), HttpStatus.OK);
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
				this.walletLedgerRepository.findAll().stream().map(WalletLedgerDTO::new).collect(Collectors.toList()),
				HttpStatus.OK);
	} catch (Exception e) {
		log.info("Error in getAll " + e.getMessage());
		return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
	}
}
@PostMapping
public ResponseEntity<?> createWalletLedger(@RequestBody WalletLedger WalletLedger) {
	log.info("Called createWalletLedger");
	try {

		return new ResponseEntity<>(this.walletLedgerRepository.save(WalletLedger),
				HttpStatus.OK);
	} catch (Exception e) {
		log.info("Error in createWalletLedger" + e.getMessage());

		if (e.getMessage().contains("ConstraintViolationException")) {
			return new ResponseEntity<>("Duplicate data", HttpStatus.BAD_REQUEST);
		} else {	
		return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
	}
}
}

@GetMapping("/{walletLedgerId}")	
public ResponseEntity<?> getWalletLedger(@PathVariable Long walletLedgerId) {
	log.info("Called getWalletLedger  "+ walletLedgerId);
	try {
		Optional<WalletLedger> walletLedger = this.walletLedgerRepository.findById(walletLedgerId);
		if (walletLedger.isPresent()) {
			WalletLedgerDTO walletLedgerDTO = new WalletLedgerDTO(walletLedger.get());
			 
			return new ResponseEntity<>(walletLedgerDTO, HttpStatus.OK);
		} else {
			throw new Exception("WalletLedger not found");
		}

		 
	} catch (Exception e) {
		log.info("Error in createWalletLedger" + e.getMessage());
		if (e.getMessage().contains("WalletLedger not found")) {
			return new ResponseEntity<>("WalletLedger not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}

@PutMapping("/{walletLedgerId}")	
public ResponseEntity<?> updateWalletLedger(@PathVariable Long walletLedgerId,@RequestBody WalletLedger requestWalletLedger) {
	log.info("Called updateWalletLedger "+ walletLedgerId);
	try {
		Optional<WalletLedger> walletLedger = this.walletLedgerRepository.findById(walletLedgerId);
		if (walletLedger.isPresent()) {
			
			walletLedger.get().setAmount(requestWalletLedger.getAmount());
			walletLedger.get().setCurrentBalance(requestWalletLedger.getCurrentBalance());
			walletLedger.get().setTransactionType(requestWalletLedger.getTransactionType());
			
			WalletLedgerDTO walletLedgerDTO = new WalletLedgerDTO(this.walletLedgerRepository.save(walletLedger.get()));
			 
			return new ResponseEntity<>(walletLedgerDTO, HttpStatus.OK);
		} else {
			throw new Exception("WalletLedger not found");
		}

		 
	} catch (Exception e) {
		log.error("Error in createwalletLedger" + e.getMessage());
		if (e.getMessage().contains("walletLedger not found")) {
			return new ResponseEntity<>("walletLedger not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}

@DeleteMapping("/{walletLedgerId}")	
public ResponseEntity<?> deleteWalletLedger(@PathVariable Long WalletLedgerId) {
	log.info("Called deleteWalletLedger  "+ WalletLedgerId);
	try {
		Optional<WalletLedger> WalletLedger= this.walletLedgerRepository.findById(WalletLedgerId);
		if (WalletLedger.isPresent()) {
			
			
			this.walletLedgerRepository.delete(WalletLedger.get());
			 
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		} else {
			throw new Exception("walletLedger not found");
		}

		 
	} catch (Exception e) {
		log.error("Error in deletewalletLedger" + e.getMessage());
		if (e.getMessage().contains("walletLedger not found")) {
			return new ResponseEntity<>("walletLedger not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}
}


}
