package com.kaliente.pos.application.mappings;

import org.modelmapper.ModelMapper;

import com.kaliente.pos.application.dtos.auth.RegisterRequestDto;
import com.kaliente.pos.application.dtos.product.ProductCreateDto;
import com.kaliente.pos.application.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.dtos.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.useraggregate.ApplicationUser;

public class DtoMapper {
	
	public DtoMapper(ModelMapper mapper) {
		mapper.createTypeMap(ApplicationUser.class, RegisterRequestDto.class);
		mapper.createTypeMap(Product.class, ProductDetailsDto.class);
		mapper.createTypeMap(Product.class, ProductCreateDto.class);
		mapper.createTypeMap(ProductCatalogue.class, ProductCatalogueDetailsDto.class);
	}
	
	
	
}
