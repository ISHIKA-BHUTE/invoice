package com.invoice.Service;

import org.springframework.web.multipart.MultipartFile;

import com.invoice.request.CustomerRequest;

public interface CustomerService {

	public String saveCustomer(CustomerRequest customerRequest) ;
	public String saveAll(MultipartFile file);
}
