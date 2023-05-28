package com.invoice.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.invoice.request.VendorRequest;
import com.invoice.response.VendorResponse;

@Service
public interface VendorService {
	 List<VendorResponse> getVendordetails();
	 VendorResponse getById(Long id);
     String save(VendorRequest vendorRequest);
     String updateVendor(Long vendorId,VendorRequest vendorRequest);
 	 String deleteVendor(Long vendorId);
	

}
