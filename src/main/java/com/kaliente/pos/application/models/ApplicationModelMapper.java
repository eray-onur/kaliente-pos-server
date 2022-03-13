package com.kaliente.pos.application.models;

import org.modelmapper.ModelMapper;

import com.kaliente.pos.application.models.dtos.auth.PersonnelDetailsDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterAdminRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterPersonnelRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductCreateDto;
import com.kaliente.pos.application.models.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.useraggregate.User;

public class ApplicationModelMapper {
	
	public ApplicationModelMapper(ModelMapper mapper) {
		mapper.createTypeMap(User.class, RegisterRequestDto.class);
		mapper.createTypeMap(User.class, RegisterPersonnelRequestDto.class);
		mapper.createTypeMap(User.class, RegisterAdminRequestDto.class);
		mapper.createTypeMap(User.class, PersonnelDetailsDto.class);
		
		mapper.createTypeMap(Product.class, ProductDetailsDto.class);
		mapper.createTypeMap(Product.class, ProductCreateDto.class);
		mapper.createTypeMap(ProductCatalogue.class, ProductCatalogueDetailsDto.class);
	}
	
	
	
}
