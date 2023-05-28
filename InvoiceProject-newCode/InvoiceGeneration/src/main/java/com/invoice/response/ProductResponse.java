package com.invoice.response;

import lombok.Data;

@Data
public class ProductResponse {
	
	private Long productId;

	private String name;

	private double price;

	private String description;


}
