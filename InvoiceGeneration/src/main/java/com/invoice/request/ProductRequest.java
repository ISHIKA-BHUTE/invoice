package com.invoice.request;

import lombok.Data;

@Data
public class ProductRequest {

	private Long productId;

	private String name;

	private double price;

	private String description;

}
