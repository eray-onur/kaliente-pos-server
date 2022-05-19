package com.kaliente.pos.application.models.dtos.productcatalogue;


public class GetProductCatalogueByIdResponseDto {
	ProductCatalogueDetailsDto productCatalogue;
	public GetProductCatalogueByIdResponseDto(ProductCatalogueDetailsDto productCatalogue) {
		this.productCatalogue = productCatalogue;
	}
	public ProductCatalogueDetailsDto getProduct() {
		return productCatalogue;
	}
	public void setProduct(ProductCatalogueDetailsDto productCatalogue) {
		this.productCatalogue = productCatalogue;
	}

}
