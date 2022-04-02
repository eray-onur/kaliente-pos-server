package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;


public class ProductDeleteResponseDto  {
	UUID deletedProductId;
	public ProductDeleteResponseDto(UUID deletedProductId) {
		this.deletedProductId = deletedProductId;
	}
	public UUID getDeletedProductId() {
		return deletedProductId;
	}
	public void setDeletedProductId(UUID deletedProductId) {
		this.deletedProductId = deletedProductId;
	}

	

}
