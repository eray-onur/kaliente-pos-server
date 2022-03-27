package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;

import com.kaliente.pos.application.models.base.BaseResponse;

public class ProductUpdateResponseDto extends BaseResponse<UUID> {

	public ProductUpdateResponseDto(UUID data) {
		super(data);
	}
	
}
