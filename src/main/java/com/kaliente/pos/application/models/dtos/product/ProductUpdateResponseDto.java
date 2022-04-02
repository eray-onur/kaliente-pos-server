package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;


public class ProductUpdateResponseDto {
	UUID updatedProductId;
	public ProductUpdateResponseDto(UUID updatedProductId) {
		this.updatedProductId = updatedProductId;
	}
	public UUID getUpdatedProductId() {
		return updatedProductId;
	}
	public void setUpdatedProductId(UUID updatedProductId) {
		this.updatedProductId = updatedProductId;
	}

	
	
}
