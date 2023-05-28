package com.invoice.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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



