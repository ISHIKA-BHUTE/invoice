package com.invoice.response;

import com.invoice.models.ProductModel;

import lombok.Data;

@Data
public class InvoiceInlineResponse {
	
	private Long lineItemId;

	private int quantity;

	private int WorkingHours;

	private int price;
	
	private ProductModel productModel;


}
