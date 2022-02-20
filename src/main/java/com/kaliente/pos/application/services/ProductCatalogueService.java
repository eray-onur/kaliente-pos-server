package com.kaliente.pos.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.dtos.productcatalogue.ProductCatalogueDetailsDto;
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
		
		public ProductCatalogueDetailsDto createNewProductCatalogue(ProductCatalogueDetailsDto dto) {
			ProductCatalogue newProductCatalogue = modelMapper.map(dto, ProductCatalogue.class);
			
			UUID parentCatalogueId = dto.getParentCatalogueId();
			if(parentCatalogueId != null) {
				Optional<ProductCatalogue> parent = catalogueRepository.findById(parentCatalogueId);
				if(parent != null) {
					newProductCatalogue.setParentCatalogue(parent.get());
				}
			}
			
			this.catalogueRepository.save(newProductCatalogue);
			return dto;
		}
		
		public ProductCatalogueDetailsDto updateProductCatalogue(ProductCatalogueDetailsDto dto) {
			Optional<ProductCatalogue> productToUpdate = this.catalogueRepository.findById(dto.getId());
			if(productToUpdate.isEmpty()) return null;
			
			productToUpdate.get().setTitle(dto.getTitle());
			productToUpdate.get().setDescription(dto.getDescription());
			
			this.catalogueRepository.save(productToUpdate.get());
			
			return dto;
		}
		
		public int deleteProductCatalogue(UUID catalogueId) {
			this.catalogueRepository.deleteById(catalogueId);
			return 1;
		}
	
	
}
