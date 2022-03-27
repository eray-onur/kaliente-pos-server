package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;

import com.kaliente.pos.application.models.base.BaseResponse;

public class ProductAddResponseDto extends BaseResponse<UUID> {

	public ProductAddResponseDto(UUID data) {
		super(data);
	}

	
	
}
