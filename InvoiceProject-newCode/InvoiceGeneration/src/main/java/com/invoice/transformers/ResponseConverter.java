package com.invoice.transformers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.invoice.models.InvoiceInlineModel;
import com.invoice.models.ProductModel;
import com.invoice.models.VendorModel;
import com.invoice.response.InvoiceInlineResponse;
import com.invoice.response.ProductResponse;
import com.invoice.response.VendorResponse;
@Component
public class ResponseConverter {
	
	public VendorResponse entityToVendorResponse(VendorModel vendorEntity) {
		   
		   VendorResponse vendorResponse = new VendorResponse();
		   
		   vendorResponse.setVendorId( vendorEntity.getVendorId());
		   vendorResponse.setVendorName( vendorEntity.getVendorName());
		   vendorResponse.setCity( vendorEntity.getCity());
		   vendorResponse.setCountry( vendorEntity.getCountry());
		   vendorResponse.setPincode( vendorEntity.getPincode());
		   vendorResponse.setProductModels(vendorEntity.getProductModels());
		   
		   return vendorResponse;
	   }
	   
	   public List<VendorResponse> toVendorResponseList(List<VendorModel> vendorEntityList){
		   
		   return vendorEntityList.stream().map(vendor-> entityToVendorResponse(vendor)).collect(Collectors.toList());
	   }
	   
	   
       public ProductResponse entityToProductResponse(ProductModel productEntity) {
		   
		   ProductResponse productResponse = new ProductResponse();
		   
		   productResponse.setProductId(productEntity.getProductId());
		   productResponse.setName(productEntity.getName());
		   productResponse.setPrice(productEntity.getPrice());
		   productResponse.setDescription(productEntity.getDescription());
		   
		   return productResponse;
	   }
	   
	   public List<ProductResponse> toProductResponseList(List<ProductModel> productEntityList){
		   
		   return productEntityList.stream().map(product-> entityToProductResponse(product)).collect(Collectors.toList());
	   }
	   
public InvoiceInlineResponse entityToInvoiceInlineResponse(InvoiceInlineModel invoiceInlineEntity) {
		   
	InvoiceInlineResponse invoiceInlineResponse = new InvoiceInlineResponse();
		   
	invoiceInlineResponse.setLineItemId( invoiceInlineEntity.getLineItemId());
	invoiceInlineResponse.setQuantity(invoiceInlineEntity.getQuantity());
	invoiceInlineResponse.setWorkingHours(invoiceInlineEntity.getWorkingHours());
	invoiceInlineResponse.setPrice(invoiceInlineEntity.getPrice());
	invoiceInlineResponse.setProductModel(invoiceInlineEntity.getProductModel());
		  
		   return invoiceInlineResponse;
	   }
	   
	   public List<InvoiceInlineResponse> toInvoiceInlineResponseList(List<InvoiceInlineModel> invoiceInlineEntityList){
		   
		   return invoiceInlineEntityList.stream().map(invoiceInline-> entityToInvoiceInlineResponse(invoiceInline)).collect(Collectors.toList());
	   }

}
