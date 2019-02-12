package com.liferay.avsi.controller;

import com.liferay.avsi.dto.ProductDTO;
import com.liferay.avsi.entity.Product;
import com.liferay.avsi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Barros
 */
@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("products")
	public ProductDTO insert(@Valid @RequestBody ProductDTO productDTO) {
		return map(productRepository.saveAndFlush(map(productDTO)));
	}

	@GetMapping("products")
	public Page<ProductDTO> listPaginated(@PageableDefault(value = 10,page = 0) Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);

		List<ProductDTO> productDTOS = new ArrayList<>();

		products.forEach(product -> productDTOS.add(map(product)));

		return new PageImpl<>(productDTOS);
	}

	private Product map(ProductDTO productDTO) {
		Product product = null;

		if (productDTO.getId() > 0) {
			product = productRepository.findById(productDTO.getId()).get();
		}
		else {
			product = new Product();
		}

		product.setName(productDTO.getName());
		product.setValue(productDTO.getValue());

		return product;
	}

	private ProductDTO map(Product product) {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setValue(product.getValue());
		productDTO.setCreatedAt(product.getCreatedAt());
		productDTO.setUpdatedAt(product.getUpdatedAt());

		return productDTO;
	}
}
