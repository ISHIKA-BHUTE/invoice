package com.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.models.ProductModel;
public interface ProductRepository extends JpaRepository<ProductModel,Long> {
	
	public List<ProductModel> findAll();
//	public VendorModel getById(Long vendorId);

}
