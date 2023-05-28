package com.invoice.response;

import java.util.List;

import com.invoice.models.ProductModel;

import lombok.Data;

@Data
public class VendorResponse {
	
	private Long vendorId;

	private String vendorName;

	private String City;

	private String Country;

	private String Pincode;
	
    private List<ProductModel> productModels;

}
