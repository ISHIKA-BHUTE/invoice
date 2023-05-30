package com.invoice.request;

import java.util.List;

import com.invoice.models.CustomerModel;
import com.invoice.models.ProductModel;

import lombok.Data;

@Data
public class VendorRequest {

	private Long vendorId;

	private String vendorName;

	private String City;

	private String Country;

	private String Pincode;
	
    private List<ProductModel> productModels;
    
    private List<CustomerModel> customerModelList;

}
