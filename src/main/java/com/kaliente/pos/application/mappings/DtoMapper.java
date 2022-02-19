package com.kaliente.pos.application.mappings;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaliente.pos.application.dtos.product.ProductCatalogueDetailsDto;
import com.kaliente.pos.application.dtos.product.ProductDetailsDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;

public class DtoMapper {
	
	public DtoMapper(ModelMapper mapper) {
		mapper.createTypeMap(Product.class, ProductDetailsDto.class);
		mapper.createTypeMap(ProductCatalogue.class, ProductCatalogueDetailsDto.class);
	}
	
	
	
}
