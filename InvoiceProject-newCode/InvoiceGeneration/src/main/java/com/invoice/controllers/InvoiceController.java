package com.invoice.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.Service.InvoiceService;
import com.invoice.models.InvoiceModel;
import com.invoice.models.VendorModel;
import com.invoice.request.InvoiceRequest;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@GetMapping("/{id}")
	public ResponseEntity<InvoiceModel> getInvoice(@PathVariable Long id){
		return ResponseEntity.ok(invoiceService.getInvoiceById(id));
	}
	
	@GetMapping("/vendor/{id}")
	public ResponseEntity<VendorModel> getInvoicevendor(@PathVariable Long id){
		return ResponseEntity.ok(invoiceService.getInvoiceById(id).getVendorModel());
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveInvoice(@RequestBody InvoiceRequest invoiceRequest){
		return ResponseEntity.ok(invoiceService.saveInvoice(invoiceRequest));
	}
	
	@GetMapping("/getInvoiceExcel/{id}")
	public File getExcelInvoice(@PathVariable Long id) {
	return invoiceService.createExcelInvoice(id);
	}
}
