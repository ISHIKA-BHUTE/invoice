package com.invoice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.Service.InvoiceInlineService;
import com.invoice.Service.VendorService;
import com.invoice.request.InvoiceInlineRequest;
import com.invoice.request.VendorRequest;
import com.invoice.response.InvoiceInlineResponse;
import com.invoice.response.VendorResponse;

@RestController
@RequestMapping("invoiceInline")
public class InvoiceInlineController {
	
	@Autowired
	private InvoiceInlineService invoiceInlineservice;

	@GetMapping("/invoiceInline")
	public List<InvoiceInlineResponse> getInvoiceInlindetails() {
		return invoiceInlineservice.getInvoiceInlinedetails();
	}

	@GetMapping("/{lineItemId}")

	public ResponseEntity<InvoiceInlineResponse> getInvoiceInlineById(@PathVariable Long  lineItemId) {

		return ResponseEntity.ok(invoiceInlineservice.getById( lineItemId));
	}
	
	@PostMapping("/saveInvoiceInline")
	public ResponseEntity<String> saveInvoiceInline(@RequestBody InvoiceInlineRequest invoiceInlineRequest){
		
		return ResponseEntity.ok(invoiceInlineservice.save(invoiceInlineRequest));
	}
	
	@PutMapping("/update/{lineItemId}")
	public ResponseEntity<String> updateInvoiceInline(@PathVariable Long lineItemId,@RequestBody InvoiceInlineRequest invoiceInlineRequest){
		return ResponseEntity.ok(invoiceInlineservice.updateInvoiceInline(lineItemId, invoiceInlineRequest));
	}
    
	@DeleteMapping("/delete/{lineItemId}")
	public String deleteInvoiceInline(@PathVariable Long  lineItemId) {
		invoiceInlineservice.deleteInvoiceInline( lineItemId);
		return "deleted";
	}

}
