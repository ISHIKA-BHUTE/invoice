package com.invoice.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

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
import com.invoice.repository.InvoiceRepository;
import com.invoice.request.InvoiceRequest;
import com.invoice.transformers.RequestConverter;

@Component
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	RequestConverter requestConverter;
	
	@Autowired
	CustomerRepository customerRepository;
	
	private final ResourceLoader resourceLoader;

    public InvoiceServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
	
	@Override
	public String saveInvoice(InvoiceRequest invoiceRequest) {
	
		InvoiceModel invoiceModel = requestConverter.invoiceRequestToModel(invoiceRequest);
		invoiceModel.setInvoiceNo(generateInvoiceNumber());
		 Long customerId = invoiceModel.getCustomerModel().getCustomerId();
		 try {
			 CustomerModel customerModel = customerRepository.findById(customerId).get();
			 invoiceModel.setCustomerModel(customerModel);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		   
		            

		   
		invoiceRepository.save(invoiceModel);
		
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
		public File createExcelInvoice(Long id) {
//			 String inputTemplate = "target/Template1.xlsx";
			  try {
				InvoiceModel invoiceModel = getInvoiceById(id);
//				InputStream inputStream = new FileInputStream(inputTemplate);
				Resource resource = resourceLoader.getResource("classpath:Template1.xlsx");
		        InputStream inputStream = resource.getInputStream();
				String companyName =invoiceModel.getVendorModel().getVendorName();
				OutputStream os = new FileOutputStream("/Users/shantanutidke/Downloads/"+companyName+"Invoice"+".xlsx");
				
				Context context = new Context();
				context.putVar("vendor",companyName );
				context.putVar("customerData", invoiceModel.getCustomerModel());
				context.putVar("vendorData", invoiceModel.getVendorModel());
				context.putVar("invoiceData", invoiceModel);
				
				JxlsHelper.getInstance().processTemplate(inputStream, os,context);
				
				File file = new File("/Users/shantanutidke/Downloads/"+companyName+"Invoice"+".xlsx");
				return file;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} 
		}



	}

