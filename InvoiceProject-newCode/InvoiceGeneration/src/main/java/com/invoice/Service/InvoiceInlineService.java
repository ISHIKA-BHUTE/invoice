package com.invoice.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.invoice.request.InvoiceInlineRequest;
import com.invoice.response.InvoiceInlineResponse;

@Service
public interface InvoiceInlineService {
	 List<InvoiceInlineResponse> getInvoiceInlinedetails();
	 InvoiceInlineResponse getById(Long lineItemId);
     String save(InvoiceInlineRequest invoiceInlineRequest);
     String updateInvoiceInline(Long lineItemId,InvoiceInlineRequest invoiceInlineRequest);
 	 String deleteInvoiceInline(Long lineItemId);

}
