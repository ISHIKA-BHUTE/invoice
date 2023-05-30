package com.invoice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.Service.ProductService;
import com.invoice.request.ProductRequest;
import com.invoice.response.ProductResponse;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/product")
	public ResponseEntity<List<ProductResponse>> getProduct() {
	 
		 return ResponseEntity.ok(productService.getProduct());
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId){
		return ResponseEntity.ok(productService.getById(productId));
	}
	
	@PostMapping("/product/save")
	public ResponseEntity<String> save(@RequestBody ProductRequest productRequest) {
		return ResponseEntity.ok(productService.save(productRequest));
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable Long productId,@RequestBody ProductRequest productRequest){
		return ResponseEntity.ok(productService.updateProduct(productId, productRequest));
	}
    
	@DeleteMapping("/delete/{productId}")
	public String deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return "deleted";
	}
	

}
