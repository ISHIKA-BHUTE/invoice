package com.invoice.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invoice.Service.ProductService;
import com.invoice.models.ProductModel;
import com.invoice.repository.ProductRepository;
import com.invoice.request.ProductRequest;
import com.invoice.response.ProductResponse;
import com.invoice.transformers.RequestConverter;
import com.invoice.transformers.ResponseConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductServicweImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ResponseConverter responseConverter;

	@Autowired
	private RequestConverter requestConverter;

	@Override
	public List<ProductResponse> getProduct() {

		log.info("Getting data");

		return responseConverter.toProductResponseList(productRepository.findAll());

	}

	@Override
	public ProductResponse getById(Long productId) {

		ProductModel model = productRepository.getById(productId);
		return responseConverter.entityToProductResponse(model);
	}

	@Override
	public String save(ProductRequest productRequest) {

		ProductModel productEntity = requestConverter.toProductModel(productRequest);

		productRepository.save(productEntity);

		return "Saved";
	}

	public String updateProduct(Long productId, ProductRequest productRequest) {


			Optional<ProductModel> user = productRepository.findById(productId);

			if (user.isPresent()) {

				ProductModel savedEntity = user.get();
				savedEntity.setProductId(productRequest.getProductId());
				savedEntity.setName(productRequest.getName());
				savedEntity.setPrice(productRequest.getPrice());
				savedEntity.setDescription(productRequest.getDescription());

				productRepository.save(savedEntity);

			}
		return null;

	}


	@Override
	public String deleteProduct(Long productId) {

		Optional<ProductModel> model = productRepository.findById(productId);
		if (model.isPresent()) {

			productRepository.deleteById(productId);

			return "Deleted";

		} else {

			return "Entity not found error";

		}

	}

	
}

