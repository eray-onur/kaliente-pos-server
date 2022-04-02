package com.kaliente.pos.application.models.dtos.productcatalogue;

import java.util.UUID;


public class ProductCatalogueUpdateResponseDto {
	UUID updatedCatalogueId;
	public ProductCatalogueUpdateResponseDto(UUID updatedCatalogueId) {
		this.updatedCatalogueId = updatedCatalogueId;
	}
	public UUID getUpdatedCatalogueId() {
		return updatedCatalogueId;
	}
	public void setUpdatedCatalogueId(UUID updatedCatalogueId) {
		this.updatedCatalogueId = updatedCatalogueId;
	}

	
}
