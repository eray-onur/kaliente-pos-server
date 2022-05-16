package com.kaliente.pos.application.models.dtos.product;


public class GetProductByIdResponseDto {
	ProductDetailsDto foundProduct;
	public GetProductByIdResponseDto(ProductDetailsDto foundProduct) {
		this.foundProduct = foundProduct;
	}
	public ProductDetailsDto getFoundProduct() {
		return foundProduct;
	}
	public void setFoundProduct(ProductDetailsDto foundProduct) {
		this.foundProduct = foundProduct;
	}

	

}
