package com.invoice.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.invoice.request.VendorRequest;
import com.invoice.response.VendorResponse;

@Service
public interface VendorService {
	 List<VendorResponse> getVendordetails();
	 VendorResponse getById(Long id);
     String save(VendorRequest vendorRequest) throws FileNotFoundException, IOException;
     String updateVendor(Long vendorId,VendorRequest vendorRequest);
 	 String deleteVendor(Long vendorId);
	byte[] getImageByteArray(String imageUrl) throws FileNotFoundException, IOException;
	

}
