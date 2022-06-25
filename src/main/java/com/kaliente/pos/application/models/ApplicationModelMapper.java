package com.kaliente.pos.application.models;

import com.kaliente.pos.application.models.order.*;
import com.kaliente.pos.domain.orderaggregate.*;
import com.kaliente.pos.domain.productaggregate.ProductCurrency;
import org.modelmapper.ModelMapper;

import com.kaliente.pos.application.requests.administration.UpdatePersonnelRequest;
import com.kaliente.pos.application.models.auth.PersonnelDetailsDto;
import com.kaliente.pos.application.requests.auth.RegisterAdminRequestDto;
import com.kaliente.pos.application.requests.auth.RegisterPersonnelRequestDto;
import com.kaliente.pos.application.requests.auth.RegisterRequestDto;
import com.kaliente.pos.application.requests.product.ProductAddRequestDto;
import com.kaliente.pos.application.models.product.ProductCreateDto;
import com.kaliente.pos.application.models.product.ProductDetailsDto;
import com.kaliente.pos.application.requests.product.ProductUpdateRequestDto;
import com.kaliente.pos.application.models.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.useraggregate.User;

public class ApplicationModelMapper {
	
	public ApplicationModelMapper(ModelMapper mapper) {
		mapper.createTypeMap(User.class, RegisterRequestDto.class);
		mapper.createTypeMap(User.class, RegisterPersonnelRequestDto.class);
		mapper.createTypeMap(User.class, RegisterAdminRequestDto.class);
		mapper.createTypeMap(User.class, PersonnelDetailsDto.class);

		mapper.createTypeMap(UpdatePersonnelRequest.class, User.class).addMapping(UpdatePersonnelRequest::getPersonnelId, (dest, v) -> dest.getId());
		

		mapper.createTypeMap(Product.class, ProductAddRequestDto.class);
		mapper.createTypeMap(Product.class, ProductUpdateRequestDto.class);
		mapper.createTypeMap(Product.class, ProductDetailsDto.class);
		mapper.createTypeMap(Product.class, ProductCreateDto.class);

		mapper
		.createTypeMap(ProductCatalogue.class, ProductCatalogueDetailsDto.class);


		mapper.createTypeMap(Order.class, OrderDetailsDto.class);
		mapper.createTypeMap(OrderProduct.class, OrderProductDto.class);
		mapper.createTypeMap(OrderTransaction.class, OrderTransactionDto.class);
		mapper.createTypeMap(OrderTransaction.class, OrderPartialTransactionDto.class);
		mapper.createTypeMap(OrderCustomer.class, OrderCustomerDto.class);

		mapper.createTypeMap(OrderCurrency.class, CurrencyModel.class);
		mapper.createTypeMap(ProductCurrency.class, CurrencyModel.class);
	}
	
}
