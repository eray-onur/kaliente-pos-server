package com.kaliente.pos.application.responses.productcatalogue;

import com.kaliente.pos.application.models.productcatalogue.ProductCatalogueDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetAllProductCataloguesResponseDto {
	List<ProductCatalogueDetailsDto> productCatalogues;
}
