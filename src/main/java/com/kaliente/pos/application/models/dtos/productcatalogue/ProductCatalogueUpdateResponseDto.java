package com.kaliente.pos.application.models.dtos.productcatalogue;

import java.util.UUID;


public class ProductCatalogueUpdateResponseDto {
	UUID updatedCatalogueId;
	String updatedCatalogueTitle;

	public ProductCatalogueUpdateResponseDto(UUID updatedCatalogueId, String updatedCatalogueTitle) {
		this.updatedCatalogueId = updatedCatalogueId;
		this.updatedCatalogueTitle = updatedCatalogueTitle;
	}
	public UUID getUpdatedCatalogueId() {
		return updatedCatalogueId;
	}
	public void setUpdatedCatalogueId(UUID updatedCatalogueId) {
		this.updatedCatalogueId = updatedCatalogueId;
	}

	public String getUpdatedCatalogueTitle() {
		return this.updatedCatalogueTitle;
	}

	public void setUpdatedCatalogueTitle(String updatedCatalogueTitle) {
		this.updatedCatalogueTitle = updatedCatalogueTitle;
	}

	
}
