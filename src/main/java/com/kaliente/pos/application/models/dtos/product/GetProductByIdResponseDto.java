package com.kaliente.pos.application.models.dtos.product;


public class GetProductByIdResponseDto {
	ProductDetailsDto product;
	public GetProductByIdResponseDto(ProductDetailsDto product) {
		this.product = product;
	}
	public ProductDetailsDto getProduct() {
		return product;
	}
	public void setProduct(ProductDetailsDto product) {
		this.product = product;
	}

	

}
