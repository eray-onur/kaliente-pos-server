package com.kaliente.pos.application.models.dtos.productcatalogue;

import java.util.UUID;


public class ProductCatalogueAddResponseDto {
	UUID addedCatalogueId;
	public ProductCatalogueAddResponseDto(UUID addedCatalogueId) {
		this.addedCatalogueId = addedCatalogueId;
	}
	public UUID getAddedCatalogueId() {
		return addedCatalogueId;
	}
	public void setAddedCatalogueId(UUID addedCatalogueId) {
		this.addedCatalogueId = addedCatalogueId;
	}

	

}
