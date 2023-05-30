package com.invoice.request;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class InvoiceRequest {

	private Long invoiceId;

	private String invoiceNo;

	private Date billingStartDate;

	private Date billingEndDate;

	private LocalDate invoiceDate;
	
	private Long customerId;

	private Long vendorId;

	List<InvoiceInlineRequest> invoiceInlineRequest;

}
