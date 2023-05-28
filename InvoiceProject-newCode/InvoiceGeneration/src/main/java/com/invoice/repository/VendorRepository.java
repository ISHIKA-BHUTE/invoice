package com.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.models.VendorModel;
@Repository
public interface VendorRepository extends JpaRepository<VendorModel,Long> {
	
	public List<VendorModel> findAll();
//	public VendorModel getById(Long vendorId);

}