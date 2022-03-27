package com.kaliente.pos.application.models.dtos.productcatalogue;

import com.kaliente.pos.application.models.base.BaseResponse;

public class GetProductCatalogueByIdResponseDto extends BaseResponse<ProductCatalogueDetailsDto> {

	public GetProductCatalogueByIdResponseDto(ProductCatalogueDetailsDto payload) {
		super(payload);
	}

}
