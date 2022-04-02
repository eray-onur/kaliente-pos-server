package com.kaliente.pos.application.models.dtos.productcatalogue;


public class GetProductCatalogueByIdResponseDto {
	ProductCatalogueDetailsDto product;
	public GetProductCatalogueByIdResponseDto(ProductCatalogueDetailsDto product) {
		this.product = product;
	}
	public ProductCatalogueDetailsDto getProduct() {
		return product;
	}
	public void setProduct(ProductCatalogueDetailsDto product) {
		this.product = product;
	}

}
