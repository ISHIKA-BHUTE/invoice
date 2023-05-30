package com.invoice.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.Service.CustomerService;
import com.invoice.models.CustomerModel;
import com.invoice.repository.CustomerRepository;
import com.invoice.request.CustomerRequest;
import com.invoice.transformers.ExcelConverter;
import com.invoice.transformers.RequestConverter;

@Component
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RequestConverter requestConverter;
	
	@Autowired
	ExcelConverter excelConverter;
	
	@Override
	public String saveCustomer(CustomerRequest customerRequest) {
		
		CustomerModel customerModel = requestConverter.customerRequestToMode(customerRequest);
		customerRepository.save(customerModel);
		return "saved";
	}

	@Override
	public String saveAll(MultipartFile file) {
				
		try {
			List<CustomerRequest> customerRequestList = excelConverter.exceltoUserModelList(file.getInputStream());
			List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
			for(CustomerRequest customer : customerRequestList) {
				customerModelList.add(requestConverter.toCustomerModel(customer));
			}
			customerRepository.saveAll(customerModelList);
			return "saved";
			
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

		
	}

}
