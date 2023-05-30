package com.invoice.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "invoices")
@Data
public class InvoiceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private Long invoiceId;

	@Column(name = "invoice_no", nullable = false)
	private String invoiceNo;

	@ManyToOne
	@JoinColumn(name = "fk_vendor_id")
	private VendorModel vendorModel;

	@ManyToOne
	@JoinColumn(name = "fk_customer_id")
	private CustomerModel customerModel;

	@Column(name = "billing_start_date")
	private Date billingStartDate;

	@Column(name = "billing_end_date")
	private Date billingEndDate;

	@Column(name = "invoice_date")
	private LocalDate invoiceDate;

	@OneToMany(mappedBy = "invoiceModel")
	private List<InvoiceInlineModel> invoiceLine;

}
