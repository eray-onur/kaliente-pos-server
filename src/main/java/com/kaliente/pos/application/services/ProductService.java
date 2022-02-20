package com.kaliente.pos.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.dtos.product.ProductCreateDto;
import com.kaliente.pos.application.dtos.product.ProductDetailsDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.productaggregate.ProductCatalogueRepository;
import com.kaliente.pos.domain.productaggregate.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductCatalogueRepository productCatalogueRepository;
	

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
	
	public Product createNewProduct(ProductCreateDto dto) {
		Product newProduct = modelMapper.map(dto, Product.class);
		
		UUID catalogueId = dto.getCatalogueId();
		if(catalogueId != null) {
			ProductCatalogue catalogue = productCatalogueRepository.getById(catalogueId);
			newProduct.setCatalogue(catalogue);
		}
		
		return this.productRepository.save(newProduct);
	}
	
	public Product updateProduct(ProductDetailsDto dto) {
		Product productToUpdate = modelMapper.map(dto, Product.class);
		return this.productRepository.save(productToUpdate);
	}
	
	public int deleteProduct(UUID productId) {
		this.productRepository.deleteById(productId);
		return 1;
	}
	
}
