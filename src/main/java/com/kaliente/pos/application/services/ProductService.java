package com.kaliente.pos.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.models.dtos.product.ProductAddRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.models.dtos.product.ProductUpdateRequestDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogueRepository;
import com.kaliente.pos.domain.productaggregate.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
//	@Autowired
//	private ProductCatalogueRepository productCatalogueRepository;
	

    @Autowired
    private ModelMapper modelMapper;
	
	
	public ProductDetailsDto getProductById(UUID productId) {
		Optional<Product> product =  this.productRepository.findById(productId);
		if(product.isEmpty()) {
			return null;
		}
		return modelMapper.map(product.get(), ProductDetailsDto.class);
	}
	
	public List<ProductDetailsDto> getAll() {
		List<Product> products = this.productRepository.findAll();
		return products.stream().map(p -> modelMapper.map(p, ProductDetailsDto.class)).toList();
	}
	
	public UUID createNewProduct(ProductAddRequestDto dto) {
		Product newProduct = modelMapper.map(dto, Product.class);
		newProduct.setCatalogue(newProduct.getCatalogue());
		
		var result = this.productRepository.save(newProduct);
		return result.getId();
	}
	
	public UUID updateProduct(ProductUpdateRequestDto dto) {
		this.productRepository.updateProduct(dto.getId(), dto.getTitle(), dto.getDescription(), dto.getPrice());
		return dto.getId();
	}
	
	public UUID deleteProduct(UUID productId) {
		this.productRepository.deleteById(productId);
		return productId;
	}
	
}
