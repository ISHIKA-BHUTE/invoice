package com.invoice.request;

import java.util.List;

import com.invoice.models.InvoiceModel;
import com.invoice.models.VendorModel;

import lombok.Data;

@Data
public class CustomerRequest {

	Long customerId;
	
	private String firstName;
	private String lastName;

	 private String customerEmail;
	 private String customerPhoneNumber;
	 private String City;
	 private String Country;
	 private String InvoiceNumber;
	 private VendorModel vendorModel;
	
}