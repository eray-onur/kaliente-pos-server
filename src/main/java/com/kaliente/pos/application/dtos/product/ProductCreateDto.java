package com.kaliente.pos.application.dtos.product;

import java.util.UUID;

import com.kaliente.pos.application.dtos.productcatalogue.ProductCatalogueDetailsDto;

public class ProductCreateDto {
	private UUID id;
	private String title;
	private String description;
	private double price;
	private UUID catalogueId;
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public UUID getCatalogueId() {
		return catalogueId;
	}
	public void setCatalogueId(UUID catalogueId) {
		this.catalogueId = catalogueId;
	}
	
}
