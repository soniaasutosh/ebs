package com.asutosh.ebs.rest;

import java.util.List;
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

import com.asutosh.ebs.domain.Customer;
import com.asutosh.ebs.dto.AddressDTO;
import com.asutosh.ebs.dto.CustomerDTO;
import com.asutosh.ebs.repository.CustomerRepositoy;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	private static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);

	private CustomerRepositoy customerRepositoy;

	public CustomerRestController(CustomerRepositoy customerRepositoy) {
		this.customerRepositoy = customerRepositoy;
	}

	@GetMapping("/ping")
	public ResponseEntity<?> checkHealth() {
		log.info("Called checkHealth");
		try {

			return new ResponseEntity<>("Working OK - " + this.customerRepositoy.count(), HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in checkHealth " + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		log.info("Called getAll");
		try {
			/**
			 * Traditional Way
			 *
			 * List<Customer> customers = this.customerRepositoy.findAll();
			 * List<CustomerDTO> customerDTOs = new ArrayList<>(); for (Customer customer :
			 * customers) { customerDTOs.add(new CustomerDTO(customer)); }
			 */

			/**
			 * Lambda Way with JAVA Stream API
			 * 
			 * .map(customer -> new CustomerDTO(customer)) ---> map(CustomerDTO::new)
			 */

			List<CustomerDTO> customerDTOs = this.customerRepositoy.findAll().stream().map(CustomerDTO::new)
					.collect(Collectors.toList());

			return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in getAll " + e.getMessage());
			return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Customer customer) {
		log.info("Called create");
		try {

			Customer customerSavedObject = this.customerRepositoy.save(customer);
			return new ResponseEntity<>(customerSavedObject, HttpStatus.OK);

		} catch (Exception e) {
			log.info("Error in create " + e.getMessage());

			if (e.getMessage().contains("ConstraintViolationException")) {
				return new ResponseEntity<>("Duplicate data", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
		log.info("Called customerId " + customerId);
		try {
			Optional<Customer> customer = this.customerRepositoy.findById(customerId);

			if (customer.isPresent()) {
				CustomerDTO customerDTO = new CustomerDTO(customer.get());
				if (customer.get().getAddresses() != null) {
					customerDTO.setAddresses(
							customer.get().getAddresses().stream().map(AddressDTO::new).collect(Collectors.toSet()));
				}
				return new ResponseEntity<>(customerDTO, HttpStatus.OK);
			} else {
				throw new Exception("Customer not found");
			}
		} catch (Exception e) {
			log.info("Error in getCustomer " + customerId + "  ::  " + e.getMessage());

			if (e.getMessage().contains("Customer not found")) {
				return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> update(@PathVariable Long customerId, @RequestBody Customer updateCustomer) {
		log.info("Called customerId " + customerId);
		try {
			Optional<Customer> customer = this.customerRepositoy.findById(customerId);

			if (customer.isPresent()) {
				customer.get().setCustomerName(updateCustomer.getCustomerName());
				customer.get().setGender(updateCustomer.getGender());
				customer.get().setMobileNumber(updateCustomer.getMobileNumber());

				return new ResponseEntity<>(this.customerRepositoy.save(customer.get()), HttpStatus.OK);
			} else {
				throw new Exception("Customer not found");
			}
		} catch (Exception e) {
			log.info("Error in getCustomer " + customerId + "  ::  " + e.getMessage());

			if (e.getMessage().contains("Customer not found")) {
				return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
		log.info("Called customerId " + customerId);
		try {
			Optional<Customer> customer = this.customerRepositoy.findById(customerId);

			if (customer.isPresent()) {
				this.customerRepositoy.delete(customer.get());
				return new ResponseEntity<>("Deleted", HttpStatus.OK);
			} else {
				throw new Exception("Customer not found");
			}
		} catch (Exception e) {
			log.info("Error in getCustomer " + customerId + "  ::  " + e.getMessage());

			if (e.getMessage().contains("Customer not found")) {
				return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Not Working", HttpStatus.BAD_REQUEST);
			}
		}
	}

}
