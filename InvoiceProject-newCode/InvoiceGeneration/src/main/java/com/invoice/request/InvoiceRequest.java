package com.invoice.request;

import java.time.LocalDate;
import java.util.Date;

import com.invoice.models.CustomerModel;
import com.invoice.models.VendorModel;

import lombok.Data;




@Data
public class InvoiceRequest {

	
	private Long invoiceId;

	private String invoiceNo;

	private Date billingStartDate;

	private Date billingEndDate;
	
	private LocalDate invoiceDate;
	
	private VendorModel vendorModel;
	
	private CustomerModel customerModel;

}
