package com.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.models.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long>{

	public List<CustomerModel> findAll();

	
}
