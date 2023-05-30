package com.invoice.request;

import lombok.Data;

@Data
public class InvoiceInlineRequest {

	private Long lineItemId;

	private Integer amount;

	private int workingHours;

	private int price;

	private Long invoiceId;

	private Long productId;
	
	public int getAmount() {
		return workingHours * price;
	}

}
