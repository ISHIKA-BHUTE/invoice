package com.invoice.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invoice.Service.InvoiceInlineService;
import com.invoice.models.InvoiceInlineModel;
import com.invoice.models.VendorModel;
import com.invoice.repository.InvoiceInlineRepository;
import com.invoice.repository.VendorRepository;
import com.invoice.request.InvoiceInlineRequest;
import com.invoice.request.VendorRequest;
import com.invoice.response.InvoiceInlineResponse;
import com.invoice.response.VendorResponse;
import com.invoice.transformers.RequestConverter;
import com.invoice.transformers.ResponseConverter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class InvoiceInlineServiceImpl implements InvoiceInlineService {
	

	@Autowired
	private InvoiceInlineRepository invoiceInlineRepository;

	@Autowired
	private ResponseConverter responseConverter;

	@Autowired
	private RequestConverter requestConverter;

	@Override
	public List<InvoiceInlineResponse> getInvoiceInlinedetails() {

		log.info("Getting data");

		return responseConverter.toInvoiceInlineResponseList(invoiceInlineRepository.findAll());

	}

	@Override
	public InvoiceInlineResponse getById(Long  lineItemId) {

		InvoiceInlineModel model = invoiceInlineRepository.getById( lineItemId);
		return responseConverter.entityToInvoiceInlineResponse(model);
	}

	@Override
	public String save(InvoiceInlineRequest invoiceInlineRequest) {

		InvoiceInlineModel invoiceInlineEntity = requestConverter.toInvoiceInlineModel(invoiceInlineRequest);

		invoiceInlineRepository.save(invoiceInlineEntity);

		return "Saved";
	}

	public String updateInvoiceInline(Long  lineItemId, InvoiceInlineRequest invoiceInlineRequest) {


			Optional<InvoiceInlineModel> user =invoiceInlineRepository.findById( lineItemId);

			if (user.isPresent()) {

				InvoiceInlineModel savedEntity = user.get();
				savedEntity.setLineItemId(invoiceInlineRequest.getLineItemId());
				savedEntity.setQuantity(invoiceInlineRequest.getQuantity());
				savedEntity.setWorkingHours(invoiceInlineRequest.getWorkingHours());
				savedEntity.setPrice(invoiceInlineRequest.getPrice());

				invoiceInlineRepository.save(savedEntity);
				return "updated";

			}else {
				return "error while updating";
			}
			

	}


	@Override
	public String deleteInvoiceInline(Long  lineItemId) {

		Optional<InvoiceInlineModel> model = invoiceInlineRepository.findById( lineItemId);
		if (model.isPresent()) {

		 invoiceInlineRepository.deleteById( lineItemId);

			return "Deleted";

		} else {

			return "Entity not found error";

		}

	}

}
