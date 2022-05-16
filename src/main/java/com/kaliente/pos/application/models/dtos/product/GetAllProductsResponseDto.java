package com.kaliente.pos.application.models.dtos.product;

import java.util.List;


public class GetAllProductsResponseDto {

	List<ProductDetailsDto> foundProducts;

	public GetAllProductsResponseDto(List<ProductDetailsDto> foundProducts) {
		this.foundProducts = foundProducts;
	}

	public List<ProductDetailsDto> getProducts() {
		return foundProducts;
	}

	public void setProducts(List<ProductDetailsDto> foundProducts) {
		this.foundProducts = foundProducts;
	}

	

	

}
