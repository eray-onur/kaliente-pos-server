package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;


public class ProductAddResponseDto {
	UUID addedProductId;
	public ProductAddResponseDto(UUID addedProductId) {
		this.addedProductId = addedProductId;
	}
	public UUID getAddedProductId() {
		return addedProductId;
	}
	public void setAddedProductId(UUID addedProductId) {
		this.addedProductId = addedProductId;
	}

	
	
	
}
