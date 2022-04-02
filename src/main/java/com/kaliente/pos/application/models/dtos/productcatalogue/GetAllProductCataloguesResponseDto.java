package com.kaliente.pos.application.models.dtos.productcatalogue;

import java.util.List;


public class GetAllProductCataloguesResponseDto {

	List<ProductCatalogueDetailsDto> productCatalogues;

	public GetAllProductCataloguesResponseDto(List<ProductCatalogueDetailsDto> productCatalogues) {
		this.productCatalogues = productCatalogues;
	}

	public List<ProductCatalogueDetailsDto> getProductCatalogues() {
		return productCatalogues;
	}

	public void setProductCatalogues(List<ProductCatalogueDetailsDto> productCatalogues) {
		this.productCatalogues = productCatalogues;
	}

	

}
