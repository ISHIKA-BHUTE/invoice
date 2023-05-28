package com.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.models.InvoiceModel;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long>{

	boolean existsByInvoiceNo(String invoiceNumber);

}
