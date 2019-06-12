package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.exception.CustomerNotFoundException;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class SpringRestController {
	// autowrie the customer service
	@Autowired
	private CustomerService customerService;
	
	// add mapping for GET/ Customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	
	// add mapping for GET single customer based on id
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		if(customer == null) {
			throw new CustomerNotFoundException("Customer Id is Not Found:- " + customerId);
		} 
		return customer;
	}
	
	// add mapping for POST/customers - add new customer
	@PostMapping("/customers")
	public Customer addNewCustomer(@RequestBody Customer customer) {
		// also just in case the pass an id in JSON ... set id to 0 
		// this is force a save of new item... instead of update
		customer.setId(0);
		customerService.saveCustomer(customer);
		return customer;
	}
	
	// add mapping for PUT/customers - update the existing customer
	
	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}
	
	// add mapping for Delete/Customer - delete the customer based on id
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		// throw exception if id not found
		if(customerService.getCustomer(customerId) == null) {
			throw new CustomerNotFoundException("Customer Id is Not Fount:- " + customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Customer deleted successfully having id : " + customerId;
	}
}
