package com.invoice.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.invoice.request.ProductRequest;
import com.invoice.response.ProductResponse;


@Service
public interface ProductService {
	
	public List<ProductResponse> getProduct();
	
	public ProductResponse getById(Long productId);
	
	public String save(ProductRequest productRequest);
	
	public String updateProduct(Long productId,ProductRequest productRequest);
	
	public String deleteProduct(Long productId);

}
