package com.kaliente.pos.application.services;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.models.dtos.product.ProductAddRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.models.dtos.product.ProductUpdateRequestDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.infrastructure.persistence.ProductHibernateRepository;

@Service
public class ProductService {

	@Autowired
	private ProductHibernateRepository productJpaRepository;

    @Autowired
    private ModelMapper modelMapper;
	
	
	public ProductDetailsDto getProductById(UUID productId) {
		Product product =  this.productJpaRepository.getProductById(productId);
		if(product == null) {
			return null;
		}
		return modelMapper.map(product, ProductDetailsDto.class);
	}
	
	public List<ProductDetailsDto> getAll() {
		Collection<Product> products = this.productJpaRepository.getProducts();
		return products.stream().map(p -> modelMapper.map(p, ProductDetailsDto.class)).toList();
	}
	
	public Product createNewProduct(ProductAddRequestDto dto) {
		// Product newProduct = modelMapper.map(dto, Product.class);
		// newProduct.setCatalogue(newProduct.getCatalogue());

		var result = this.productJpaRepository.addProduct(dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getCatalogueId());
		
		// var result = this.productRepository.save(newProduct);
		return result;
	}
	
	public Product updateProduct(ProductUpdateRequestDto dto) {
		var updatedProduct = this.productJpaRepository.updateProduct(dto.getId(), dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getCatalogueId());
		return updatedProduct;
	}
	
	public UUID deleteProduct(UUID productId) {
		this.productJpaRepository.archiveProduct(productId);
		return productId;
	}
	
}
