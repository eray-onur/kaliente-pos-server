package com.kaliente.pos.application.models.dtos.productcatalogue;

import java.util.UUID;


public class ProductCatalogueAddResponseDto {
	UUID addedCatalogueId;
	String addedCatalogueTitle;
	public ProductCatalogueAddResponseDto(UUID addedCatalogueId, String addedCatalogueTitle) {
		this.addedCatalogueId = addedCatalogueId;
		this.addedCatalogueTitle = addedCatalogueTitle;
	}
	public UUID getAddedCatalogueId() {
		return addedCatalogueId;
	}
	public void setAddedCatalogueId(UUID addedCatalogueId) {
		this.addedCatalogueId = addedCatalogueId;
	}

	public String getAddedCatalogueTitle() {
		return this.addedCatalogueTitle;
	}

	public void setAddedProductTitle(String addedCatalogueTitle) {
		this.addedCatalogueTitle = addedCatalogueTitle;
	}

	

}
