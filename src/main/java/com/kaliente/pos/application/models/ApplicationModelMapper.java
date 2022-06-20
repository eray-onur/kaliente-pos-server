package com.kaliente.pos.application.models;

import com.kaliente.pos.application.models.dtos.order.*;
import com.kaliente.pos.domain.orderaggregate.Order;
import com.kaliente.pos.domain.orderaggregate.OrderCustomer;
import com.kaliente.pos.domain.orderaggregate.OrderProduct;
import com.kaliente.pos.domain.orderaggregate.OrderTransaction;
import org.modelmapper.ModelMapper;

import com.kaliente.pos.application.models.dtos.administration.UpdatePersonnelRequest;
import com.kaliente.pos.application.models.dtos.auth.PersonnelDetailsDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterAdminRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterPersonnelRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductAddRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductCreateDto;
import com.kaliente.pos.application.models.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.models.dtos.product.ProductUpdateRequestDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.useraggregate.User;
import org.modelmapper.PropertyMap;

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
	}
	
}
