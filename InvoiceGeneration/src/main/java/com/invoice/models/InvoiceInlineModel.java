package com.invoice.models;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "invoice_line")
@Data

public class InvoiceInlineModel  {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_item_id")
    private Long lineItemId;

    @Column(name = "amount")
//    private int quantity;
    private Integer amount;

    public Integer getAmount() {
        if (workingHours != 0 && price != 0) {
            return workingHours * price;
        }
        return null;
    }
    
    @Column(name = "working_hours")
    private int workingHours;
    
    @Column(name = "price")
    private int price;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "fk_product_id")
    private ProductModel productModel;

    @ManyToOne
    @JoinColumn(name = "fk_invoice_id")
    @JsonIgnore
    private InvoiceModel invoiceModel;

}

