package com.kaliente.pos.application.models.dtos.product;

import java.util.List;


public class GetAllProductsResponseDto {

	List<ProductDetailsDto> products;

	public GetAllProductsResponseDto(List<ProductDetailsDto> products) {
		this.products = products;
	}

	public List<ProductDetailsDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetailsDto> products) {
		this.products = products;
	}

	

	

}
