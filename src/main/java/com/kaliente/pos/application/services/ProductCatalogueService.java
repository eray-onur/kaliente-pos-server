package com.kaliente.pos.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueAddRequestDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueUpdateRequestDto;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.productaggregate.ProductCatalogueRepository;

@Service
public class ProductCatalogueService {
	
	@Autowired
	private ProductCatalogueRepository catalogueRepository;
	
	 @Autowired
	    private ModelMapper modelMapper;
		
		
		public ProductCatalogueDetailsDto getProductCatalogueById(UUID catalogueId) {
			Optional<ProductCatalogue> product =  this.catalogueRepository.findById(catalogueId);
			if(product.isEmpty()) {
				return null;
			}
			return modelMapper.map(product.get(), ProductCatalogueDetailsDto.class);
		}
		
		public List<ProductCatalogueDetailsDto> getAllCatalogues() {
			List<ProductCatalogue> productCatalogues = this.catalogueRepository.findAll();
			return productCatalogues.stream().map(p -> modelMapper.map(p, ProductCatalogueDetailsDto.class)).toList();
		}
		
		public UUID createNewProductCatalogue(ProductCatalogueAddRequestDto dto) {
			ProductCatalogue newProductCatalogue = modelMapper.map(dto, ProductCatalogue.class);
			
			var createdProduct = this.catalogueRepository.save(newProductCatalogue);
			return createdProduct.getId();
		}
		
		public UUID updateProductCatalogue(ProductCatalogueUpdateRequestDto dto) {
			
			this.catalogueRepository.updateCatalogue(dto.getId(), dto.getTitle(), dto.getDescription());
			
			return dto.getId();
		}
		
		public UUID deleteProductCatalogue(UUID catalogueId) {
			this.catalogueRepository.deleteById(catalogueId);
			return catalogueId;
		}
	
	
}
