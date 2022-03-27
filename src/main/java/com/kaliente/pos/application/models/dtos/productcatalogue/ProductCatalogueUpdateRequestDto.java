package com.kaliente.pos.application.models.dtos.productcatalogue;

import java.util.UUID;

public class ProductCatalogueUpdateRequestDto {
	private UUID id;
	private String title;
	private String description;
	private UUID parentCatalogueId;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UUID getParentCatalogueId() {
		return parentCatalogueId;
	}
	public void setParentCatalogueId(UUID parentCatalogueId) {
		this.parentCatalogueId = parentCatalogueId;
	}
}
