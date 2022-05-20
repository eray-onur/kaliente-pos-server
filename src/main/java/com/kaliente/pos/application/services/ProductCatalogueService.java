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
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.productaggregate.ProductCatalogueRepository;
import com.kaliente.pos.infrastructure.persistence.ProductCatalogueJpaRepository;

@Service
public class ProductCatalogueService {
	
	@Autowired
	private ProductCatalogueRepository catalogueRepository;

	@Autowired
	private ProductCatalogueJpaRepository catalogueJpaRepository;
	
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
			
			var createdProductCatalogue = this.catalogueJpaRepository.addProductCatalogue(dto.getTitle(), dto.getDescription(), dto.getParentCatalogueId());
			return createdProductCatalogue.getId();
		}
		
		public UUID updateProductCatalogue(ProductCatalogueUpdateRequestDto dto) {
			var createdProductCatalogue = this.catalogueJpaRepository.updateProductCatalogue(
				dto.getId(), 
				dto.getTitle(),
				dto.getDescription(), 
				dto.getParentCatalogueId()
			);
			
			return createdProductCatalogue.getId();
		}
		
		public UUID deleteProductCatalogue(UUID catalogueId) {
			this.catalogueRepository.deleteById(catalogueId);
			return catalogueId;
		}
	
	
}
