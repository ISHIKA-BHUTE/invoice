package com.invoice.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "vendorId")
	private List<CustomerModel> customerModels;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_id")
	@JsonManagedReference
	private List<ProductModel> productModels;

}
