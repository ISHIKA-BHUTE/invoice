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
@Table(name = "vendor")
@Data

public class VendorModel {
	
	   

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "vendor_id")
	    private Long vendorId;

	    @Column(name = "vendor_name")
	    private String vendorName;

	    @Column(name = "vendor_city")
	    private String city;

	    @Column(name = "vendor_country")
	    private String country;

	    @Column(name = "pincode")
	    private String pincode;

	    @OneToMany(mappedBy = "vendorModel", cascade = CascadeType.ALL)
	    @JsonIgnore
	    private List<InvoiceModel> invoiceModels;

	    @OneToMany(cascade=CascadeType.ALL)
	    @JoinColumn(name="vendorId")
	    private List<CustomerModel> customerModels;
	    
	    @OneToMany(cascade=CascadeType.ALL)
	    @JoinColumn(name="vendor_id")
	    @JsonManagedReference
	    private List<ProductModel> productModels;

}
