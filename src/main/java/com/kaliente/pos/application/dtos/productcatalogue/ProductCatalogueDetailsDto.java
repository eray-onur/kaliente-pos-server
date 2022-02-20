package com.kaliente.pos.application.dtos.productcatalogue;

import java.util.UUID;

public class ProductCatalogueDetailsDto {
	private UUID id;
	private String title;
	private String description;
	private ProductCatalogueDetailsDto parentCatalogue;
	
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
	public ProductCatalogueDetailsDto getParentCategory() {
		return parentCatalogue;
	}
	public void setParentCategory(ProductCatalogueDetailsDto parentCatalogue) {
		this.parentCatalogue = parentCatalogue;
	}
	
	
	
}
