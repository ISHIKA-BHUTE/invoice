package com.invoice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.Service.VendorService;
import com.invoice.request.VendorRequest;
import com.invoice.response.VendorResponse;

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private VendorService vendorservice;

	@GetMapping
	public List<VendorResponse> getVendordetails() {
		return vendorservice.getVendordetails();
	}

	@GetMapping("/{vendorId}")

	public ResponseEntity<VendorResponse> getVendorById(@PathVariable Long vendorId) {

		return ResponseEntity.ok(vendorservice.getById(vendorId));
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveVendorDetails(@RequestBody VendorRequest vendorRequest){
		
		return ResponseEntity.ok(vendorservice.save(vendorRequest));
	}
	
	@PutMapping("/update/{vendorId}")
	public ResponseEntity<String> updateVendor(@PathVariable Long vendorId,@RequestBody VendorRequest vendorRequest){
		return ResponseEntity.ok(vendorservice.updateVendor(vendorId, vendorRequest));
	}
    
	@DeleteMapping("/delete/{vendorId}")
	public String deleteUser(@PathVariable Long vendorId) {
		vendorservice.deleteVendor(vendorId);
		return "deleted";
	}
	

}
