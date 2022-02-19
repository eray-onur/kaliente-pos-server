package com.kaliente.pos.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.dtos.product.ProductDetailsDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.productaggregate.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	

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
	
	public Product createNewProduct(ProductDetailsDto dto) {
		Product newProduct = modelMapper.map(dto, Product.class);
		System.out.println(newProduct.toString());
		return this.productRepository.save(newProduct);
	}
	
	public Product updateProduct(ProductDetailsDto dto) {
		Optional<Product> productToUpdate = this.productRepository.findById(dto.getId());
		if(productToUpdate.isEmpty()) return null;
		
		productToUpdate.get().setTitle(dto.getTitle());
		productToUpdate.get().setDescription(dto.getDescription());
		productToUpdate.get().setPrice(dto.getPrice());
		
		return this.productRepository.save(productToUpdate.get());
	}
	
	public int deleteProduct(UUID productId) {
		this.productRepository.deleteById(productId);
		return 1;
	}
	
}
