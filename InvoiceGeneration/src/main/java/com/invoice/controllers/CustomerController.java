package com.invoice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.Service.CustomerService;
import com.invoice.models.CustomerModel;
import com.invoice.repository.CustomerRepository;
import com.invoice.request.CustomerRequest;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	 
	@Autowired
    CustomerService customerService;
	
	@PostMapping("/save")
	public String saveUsers(@RequestBody CustomerRequest customerRequest) {
		
		return customerService.saveCustomer(customerRequest);
	}

	@PostMapping(path="/upload/excel")
	public String addUsersFromExcel(@RequestParam("file") MultipartFile file){
		return customerService.saveAll(file);
	}

	
	
}
