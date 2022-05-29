package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;


public class ProductUpdateResponseDto {
	UUID updatedProductId;
	String updatedProductTitle;

	public ProductUpdateResponseDto(UUID updatedProductId, String updatedProductTitle) {
		this.updatedProductId = updatedProductId;
		this.updatedProductTitle = updatedProductTitle;
	}


	public UUID getUpdatedProductId() {
		return updatedProductId;
	}
	public void setUpdatedProductId(UUID updatedProductId) {
		this.updatedProductId = updatedProductId;
	}

	public String getUpdatedProductTitle() {
		return updatedProductTitle;
	}

	public void setUpdatedProductTitle(String title) {
		this.updatedProductTitle = title;
	}
	
	
}
