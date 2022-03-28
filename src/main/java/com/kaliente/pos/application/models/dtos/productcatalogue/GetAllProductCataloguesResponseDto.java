package com.kaliente.pos.application.models.dtos.productcatalogue;

import java.util.List;

import com.kaliente.pos.application.models.base.BaseResponse;

public class GetAllProductCataloguesResponseDto extends BaseResponse<List<ProductCatalogueDetailsDto>> {

	public GetAllProductCataloguesResponseDto(List<ProductCatalogueDetailsDto> payload) {
		super(payload);
	}

}
