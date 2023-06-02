package com.invoice.ServiceImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.invoice.models.*;

import org.apache.commons.codec.binary.Base64;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.invoice.Service.InvoiceService;
import com.invoice.models.CustomerModel;
import com.invoice.models.InvoiceModel;
import com.invoice.repository.CustomerRepository;
import com.invoice.repository.InvoiceInlineRepository;
import com.invoice.repository.InvoiceRepository;
import com.invoice.request.InvoiceRequest;
import com.invoice.transformers.RequestConverter;

@Component
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	RequestConverter requestConverter;

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	InvoiceInlineRepository invoiceInlineRepository;

	private final ResourceLoader resourceLoader;

	public InvoiceServiceImpl(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public String saveInvoice(InvoiceRequest invoiceRequest) {
		InvoiceModel invoiceModel = requestConverter.invoiceRequestToModel(invoiceRequest);
		String invoiceNumber = generateInvoiceNumber();
		invoiceModel.setInvoiceNo(invoiceNumber); 
		InvoiceModel modelSaved = invoiceRepository.save(invoiceModel);
		
		Long inviceId = modelSaved.getInvoiceId();
		invoiceRequest.setInvoiceId(inviceId);
		List<InvoiceInlineModel> invoiceInlineModel = requestConverter.invoiceRequestToInvoiceInline(invoiceRequest);
		invoiceInlineRepository.saveAll(invoiceInlineModel);
		return "Saved";
	}

	@Override
	public InvoiceModel getInvoiceById(Long invoiceId) {

		return invoiceRepository.findById(invoiceId).get();
	}

	public String generateInvoiceNumber() {
		String invoiceNumber;
		boolean isUnique = false;

		while (!isUnique) {
			invoiceNumber = UUID.randomUUID().toString().substring(0, 8); // Generate a random invoice number

			if (!invoiceRepository.existsByInvoiceNo(invoiceNumber)) {
				isUnique = true;
				return invoiceNumber;
			}
		}

		return null;
	}

	@Override
	public File createExcelInvoice(Long id , String templateFormat) {
		try {
			InvoiceModel invoiceModel = getInvoiceById(id);
			 Resource resource;
		        if (templateFormat.equals("01")) {
		            resource = resourceLoader.getResource("classpath:Template01.xlsx");
		        } else if (templateFormat.equals("02")) {
		            resource = resourceLoader.getResource("classpath:Template02.xlsx");
		        } else {
		            throw new IllegalArgumentException("Invalid template format: " + templateFormat);
		        }
			InputStream inputStream = resource.getInputStream();
			String invoiceNo = invoiceModel.getInvoiceNo();
			OutputStream os = new FileOutputStream(
					"C:\\Users\\rinku\\Downloads\\" + invoiceNo + "-Invoice" + ".xlsx");

			Context context = new Context();
			context.putVar("vendor",  invoiceModel.getVendorModel().getVendorName());
			context.putVar("customerData", invoiceModel.getCustomerModel());
			context.putVar("vendorData", invoiceModel.getVendorModel());
			context.putVar("invoiceData", invoiceModel);
			context.putVar("inlineData" , invoiceModel.getInvoiceLine());
			context.putVar("image" , invoiceModel.getVendorModel().getVendorImage()); 
					
			JxlsHelper.getInstance().processTemplate(inputStream, os, context);

			File file = new File("C:\\Users\\rinku\\Downloads\\" + invoiceNo + "-Invoice" + ".xlsx");
			return file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
	}


}
