package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;


public class ProductAddResponseDto {
	UUID addedProductId;
	String addedProductTitle;

	public ProductAddResponseDto(UUID addedProductId, String addedProductTitle) {
		this.addedProductId = addedProductId;
		this.addedProductTitle = addedProductTitle;
	}
	
	public UUID getAddedProductId() {
		return addedProductId;
	}
	public void setAddedProductId(UUID addedProductId) {
		this.addedProductId = addedProductId;
	}

	public String getAddedProductTitle() {
		return addedProductTitle;
	}

	public void setAddedProductTitle(String addedProductTitle) {
		this.addedProductTitle = addedProductTitle;
	}
	
	
}
