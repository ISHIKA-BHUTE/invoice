package com.invoice.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
@Data

public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "vendor_id")
	@JsonBackReference
	private VendorModel vendorModel;

	@OneToOne(mappedBy = "productModel", cascade = CascadeType.MERGE)
	// @JsonBackReference
	@JsonIgnore
	private InvoiceInlineModel invoiceInline;

}
