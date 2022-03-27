package com.kaliente.pos.application.models.dtos.product;

import java.util.List;

import com.kaliente.pos.application.models.base.BaseResponse;

public class GetAllProductsResponseDto extends BaseResponse<List<ProductDetailsDto>> {

	public GetAllProductsResponseDto(List<ProductDetailsDto> payload) {
		super(payload);
	}

}
