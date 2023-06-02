package com.invoice.transformers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invoice.Service.VendorService;
import com.invoice.ServiceImpl.VendorServiceImpl;
import com.invoice.models.CustomerModel;
import com.invoice.models.InvoiceInlineModel;
import com.invoice.models.InvoiceModel;
import com.invoice.models.ProductModel;
import com.invoice.models.VendorModel;
import com.invoice.repository.CustomerRepository;
import com.invoice.repository.InvoiceRepository;
import com.invoice.repository.ProductRepository;
import com.invoice.repository.VendorRepository;
import com.invoice.request.CustomerRequest;
import com.invoice.request.InvoiceInlineRequest;
import com.invoice.request.InvoiceRequest;
import com.invoice.request.ProductRequest;
import com.invoice.request.VendorRequest;

@Component
public class RequestConverter {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private VendorService vendorService;

	public VendorModel toVendorModel(VendorRequest vendorRequest) throws FileNotFoundException, IOException {

		if (vendorRequest == null) {
			return null;
		}
		VendorModel vendorModel = new VendorModel();

		vendorModel.setVendorName(vendorRequest.getVendorName());
		vendorModel.setCity(vendorRequest.getCity());
		vendorModel.setCountry(vendorRequest.getCountry());
		vendorModel.setPincode(vendorRequest.getPincode());
//		vendorModel.setVendorImage(vendorRequest.getVendorImage());

		vendorModel.setProductModels(vendorRequest.getProductModels());
		byte[] vendorImage = vendorService.getImageByteArray(vendorRequest.getVendorImage());
		vendorModel.setVendorImage(vendorImage);

		return vendorModel;

	}

	public ProductModel toProductModel(ProductRequest productRequest) {

		if (productRequest == null) {
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

		if (invoiceInlineRequest == null) {
			return null;
		}
		InvoiceInlineModel invoiceInlineModel = new InvoiceInlineModel();

		invoiceInlineModel.setLineItemId(invoiceInlineRequest.getLineItemId());
//		invoiceInlineModel.setQuantity(invoiceInlineRequest.getQuantity());
		invoiceInlineModel.setWorkingHours(invoiceInlineRequest.getWorkingHours());
		invoiceInlineModel.setPrice(invoiceInlineRequest.getPrice());
		
	    // Calculate the quantity based on workingHours and price
	    int quantity = invoiceInlineRequest.getWorkingHours() * invoiceInlineRequest.getPrice();
	    invoiceInlineModel.setAmount(quantity);

		ProductModel productModel = productRepository.findByProductId(invoiceInlineRequest.getProductId());
		InvoiceModel invoiceModel = invoiceRepository.findByInvoiceId(invoiceInlineRequest.getInvoiceId());

		invoiceInlineModel.setProductModel(productModel);
		invoiceInlineModel.setInvoiceModel(invoiceModel);
		

		return invoiceInlineModel;
	}

	public InvoiceModel invoiceRequestToModel(InvoiceRequest invoiceRequest) {
		InvoiceModel invoiceModel = new InvoiceModel();
		invoiceModel.setBillingEndDate(invoiceRequest.getBillingEndDate());
		invoiceModel.setBillingStartDate(invoiceRequest.getBillingStartDate());
		invoiceModel.setInvoiceNo("#100");
		invoiceModel.setInvoiceDate(LocalDate.now());

		CustomerModel customerModel = customerRepository.findByCustomerId(invoiceRequest.getCustomerId());
		VendorModel vendorModel = vendorRepository.findByVendorId(invoiceRequest.getVendorId());

		invoiceModel.setCustomerModel(customerModel);
		invoiceModel.setVendorModel(vendorModel);
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
		if (customerRequest == null) {
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

	public List<InvoiceInlineModel> invoiceRequestToInvoiceInline(InvoiceRequest invoiceRequest) {
		List<InvoiceInlineModel> list = new ArrayList<>();
		for (InvoiceInlineRequest inlineRequest : invoiceRequest.getInvoiceInlineRequest()) {
			InvoiceInlineModel obj = new InvoiceInlineModel();
			obj.setAmount(inlineRequest.getAmount());
			obj.setWorkingHours(inlineRequest.getWorkingHours());
			obj.setPrice(inlineRequest.getPrice());

			ProductModel productModel = productRepository.findByProductId(inlineRequest.getProductId());
			obj.setProductModel(productModel);

			InvoiceModel invoiceModel = invoiceRepository.findByInvoiceId(invoiceRequest.getInvoiceId());
			obj.setInvoiceModel(invoiceModel);

			list.add(obj);
		}
		return list;
	}
}
