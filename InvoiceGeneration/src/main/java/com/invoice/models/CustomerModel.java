package com.invoice.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "customer")
@Data

public class CustomerModel {
	

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "customer_id")
	    private Long customerId;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name")
	    private String lastName;

	    @Column(name = "customer_email")
	    private String customerEmail;

	    @Column(name = "customer_phone_number")
	    private String customerPhoneNumber;
	    
	    @Column(name = "city")
	    private String city;

	    @Column(name = "country")
	    private String country;
	    
	  
	    
	    @OneToMany(mappedBy = "customerModel", cascade = CascadeType.ALL)
	    @JsonIgnore
	   	private List<InvoiceModel> invoiceModels;
	    
	    @ManyToOne
	    @JoinColumn(name = "vendorId")
	    @JsonIgnore
	    private VendorModel vendorModel;


	    
	}



