package com.cms.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cms.entity.Customer;
import com.cms.entity.User;
import com.cms.exception.AdminException;
import com.cms.exception.CustomerException;
import com.cms.exception.LoginException;
import com.cms.exception.UserException;
import com.cms.service.CustomerServices;
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServices customerService;

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException {

		Customer added_customer = customerService.addCustomer(customer);

		return new ResponseEntity<Customer>(added_customer, HttpStatus.ACCEPTED);

	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestParam String key,
			@Valid @RequestBody Customer customer) throws CustomerException, LoginException {

		Customer updated_customer = customerService.updateCustomer(key, customer);

		return new ResponseEntity<Customer>(updated_customer, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<String> removeCustomerHandler(@RequestParam String key, @Valid @RequestBody User user)
			throws CustomerException, LoginException, UserException {

		String result = customerService.removeCustomer(key, user);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/viewCustomer")
	public ResponseEntity<Customer> viewCustomerHandler(@RequestParam String key,
			@Valid @RequestParam Integer customer_Id) throws CustomerException, LoginException {

		Customer customer = customerService.viewCustomer(key, customer_Id);

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// Check for Admin Role
	@GetMapping("/allCustomers")
	public ResponseEntity<List<Customer>> viewAllCustomersHandler(@RequestParam String key)
			throws CustomerException, LoginException, AdminException {

		List<Customer> listofcustomers = customerService.viewAllCustomers(key);

		return new ResponseEntity<List<Customer>>(listofcustomers, HttpStatus.OK);
	
}
}

