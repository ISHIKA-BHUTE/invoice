package com.invoice.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invoice.Service.VendorService;
import com.invoice.models.VendorModel;
import com.invoice.repository.VendorRepository;
import com.invoice.request.VendorRequest;
import com.invoice.response.VendorResponse;
import com.invoice.transformers.RequestConverter;
import com.invoice.transformers.ResponseConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private ResponseConverter responseConverter;

	@Autowired
	private RequestConverter requestConverter;

	@Override
	public List<VendorResponse> getVendordetails() {

		log.info("Getting data");

		return responseConverter.toVendorResponseList(vendorRepository.findAll());

	}

	@Override
	public VendorResponse getById(Long vendorId) {

		VendorModel model = vendorRepository.getById(vendorId);
		return responseConverter.entityToVendorResponse(model);
	}

	@Override
	public String save(VendorRequest vendorRequest) {

		VendorModel vendorEntity = requestConverter.toVendorModel(vendorRequest);

		vendorRepository.save(vendorEntity);

		return "Saved";
	}

	public String updateVendor(Long vendorId, VendorRequest vendorRequest) {


			Optional<VendorModel> user = vendorRepository.findById(vendorId);

			if (user.isPresent()) {

				VendorModel savedEntity = user.get();
				savedEntity.setVendorId(vendorRequest.getVendorId());
				savedEntity.setVendorName(vendorRequest.getVendorName());
				savedEntity.setCity(vendorRequest.getCity());
				savedEntity.setCountry(vendorRequest.getCountry());
				savedEntity.setPincode(vendorRequest.getPincode());

				vendorRepository.save(savedEntity);
				return "updated";

			}else {
				return "error while updating";
			}
			

	}


	@Override
	public String deleteVendor(Long vendorId) {

		Optional<VendorModel> model = vendorRepository.findById(vendorId);
		if (model.isPresent()) {

			vendorRepository.deleteById(vendorId);

			return "Deleted";

		} else {

			return "Entity not found error";

		}

	}

}
