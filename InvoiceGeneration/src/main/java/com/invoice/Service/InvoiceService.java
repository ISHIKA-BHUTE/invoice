package com.invoice.Service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.invoice.models.InvoiceModel;
import com.invoice.request.InvoiceRequest;

@Service
public interface InvoiceService {

	public String saveInvoice(InvoiceRequest invoiceRequest);
	
	public InvoiceModel getInvoiceById(Long invoiceId);
	
	public File createExcelInvoice(Long id);
	
}
