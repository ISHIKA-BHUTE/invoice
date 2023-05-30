package com.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.models.InvoiceInlineModel;

public interface InvoiceInlineRepository extends JpaRepository<InvoiceInlineModel,Long> {

}
