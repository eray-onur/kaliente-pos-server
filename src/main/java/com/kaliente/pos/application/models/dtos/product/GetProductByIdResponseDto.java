package com.kaliente.pos.application.models.dtos.product;

import com.kaliente.pos.application.models.base.BaseResponse;

public class GetProductByIdResponseDto extends BaseResponse<ProductDetailsDto> {

	public GetProductByIdResponseDto(ProductDetailsDto payload) {
		super(payload);
	}

}
