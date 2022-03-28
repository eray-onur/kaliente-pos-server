package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;

import com.kaliente.pos.application.models.base.BaseResponse;

public class ProductDeleteResponseDto extends BaseResponse<UUID> {

	public ProductDeleteResponseDto(UUID data) {
		super(data);
	}

}
