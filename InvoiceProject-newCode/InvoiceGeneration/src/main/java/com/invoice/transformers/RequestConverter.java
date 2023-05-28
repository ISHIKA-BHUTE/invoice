package com.invoice.transformers;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.invoice.models.CustomerModel;
import com.invoice.models.InvoiceInlineModel;
import com.invoice.models.InvoiceModel;
import com.invoice.models.ProductModel;
import com.invoice.models.VendorModel;
import com.invoice.request.CustomerRequest;
import com.invoice.request.InvoiceInlineRequest;
import com.invoice.request.InvoiceRequest;
import com.invoice.request.ProductRequest;
import com.invoice.request.VendorRequest;

@Component
public class RequestConverter {
	
	public VendorModel toVendorModel(VendorRequest vendorRequest) {

		if(vendorRequest==null) {
			return null;
		}
		VendorModel vendorModel = new VendorModel();

		vendorModel.setVendorName(vendorRequest.getVendorName());
		vendorModel.setCity(vendorRequest.getCity());
		vendorModel.setCountry(vendorRequest.getCountry());
		vendorModel.setPincode(vendorRequest.getPincode());
		vendorModel.setProductModels(vendorRequest.getProductModels());

		return vendorModel;

	}
	
	public ProductModel toProductModel(ProductRequest productRequest) {

		if(productRequest==null) {
			return null;
		}
		ProductModel productModel = new ProductModel();

		productModel.setProductId(productRequest.getProductId());
		productModel.setName(productRequest.getName());
		productModel.setPrice(productRequest.getPrice());
		productModel.setDescription(productRequest.getDescription());

		return productModel;

	}
	
	public InvoiceInlineModel toInvoiceInlineModel(InvoiceInlineRequest invoiceInlineRequest) {

		if(invoiceInlineRequest==null) {
			return null;
		}
		InvoiceInlineModel invoiceInlineModel = new InvoiceInlineModel();

		invoiceInlineModel.setLineItemId( invoiceInlineRequest.getLineItemId());
		invoiceInlineModel.setQuantity( invoiceInlineRequest.getQuantity());
		invoiceInlineModel.setWorkingHours( invoiceInlineRequest.getWorkingHours());
		invoiceInlineModel.setPrice( invoiceInlineRequest.getPrice());
		invoiceInlineModel.setProductModel(invoiceInlineRequest.getProductModel());

		return invoiceInlineModel;

	}

	public InvoiceModel invoiceRequestToModel(InvoiceRequest invoiceRequest) {
		
		InvoiceModel invoiceModel = new InvoiceModel();
		
		invoiceModel.setBillingEndDate(invoiceRequest.getBillingEndDate());
		invoiceModel.setBillingStartDate(invoiceRequest.getBillingStartDate());
		invoiceModel.setCustomerModel(invoiceRequest.getCustomerModel());
		invoiceModel.setInvoiceNo("#100");
		invoiceModel.setVendorModel(invoiceRequest.getVendorModel());
		invoiceModel.setInvoiceDate(LocalDate.now());
		
		return invoiceModel;
		
	}
	
	public CustomerModel customerRequestToMode(CustomerRequest customerRequest) {
		CustomerModel customerModel = new CustomerModel();
		customerModel.setVendorModel(customerRequest.getVendorModel());
		customerModel.setFirstName(customerRequest.getFirstName());
		customerModel.setCity(customerRequest.getCity());
		customerModel.setCustomerEmail(customerRequest.getCustomerEmail());
		return customerModel;
	}
	
	public CustomerModel toCustomerModel(CustomerRequest customerRequest) {
		if(customerRequest==null) {
			return null;
		}
		CustomerModel customerModel = new CustomerModel();
		customerModel.setFirstName(customerRequest.getFirstName());
		customerModel.setLastName(customerRequest.getLastName());
		customerModel.setCity(customerRequest.getCity());
		customerModel.setCountry(customerRequest.getCountry());
		customerModel.setCustomerId(customerRequest.getCustomerId());
		customerModel.setCustomerPhoneNumber(customerRequest.getCustomerPhoneNumber());
		customerModel.setCustomerEmail(customerRequest.getCustomerEmail());
		
		return customerModel;
		
	}
}
