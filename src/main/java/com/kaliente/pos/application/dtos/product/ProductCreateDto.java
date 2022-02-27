package com.kaliente.pos.application.dtos.product;

import java.util.UUID;

import com.kaliente.pos.application.dtos.productcatalogue.ProductCatalogueDetailsDto;

public class ProductCreateDto {
	private UUID id;
	private String title;
	private String description;
	private double price;
	private ProductCatalogueDetailsDto catalogue;
	
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
	public ProductCatalogueDetailsDto getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(ProductCatalogueDetailsDto productCategoryDetails) {
		this.catalogue = productCategoryDetails;
	}
	
	
	
}
