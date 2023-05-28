package com.invoice.models;


import java.io.Serializable;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "invoice_line")
@Data

public class InvoiceInlineModel  {

   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_item_id")
    private Long lineItemId;

    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "working_hours")
    private int workingHours;
    
    @Column(name = "price")
    private int price;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "fk_product_id")
    @JsonIgnore
    private ProductModel productModel;

    @ManyToOne
    @JoinColumn(name = "fk_invoice_id")
    private InvoiceModel invoiceModel;

}

