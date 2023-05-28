package com.invoice.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data

public class ProductModel  {

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
    @JoinColumn(name="vendor_id")
    @JsonBackReference
    private VendorModel vendorModel;
    
    @OneToOne(mappedBy="productModel",cascade=CascadeType.MERGE)
    //@JsonBackReference
    @JsonIgnore
    private  InvoiceInlineModel invoiceInline;

}

