package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
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
		return customerService.getCustomer(customerId);
	}
	
}
