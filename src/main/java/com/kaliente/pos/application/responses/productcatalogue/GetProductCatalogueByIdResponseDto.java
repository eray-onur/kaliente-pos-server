package com.kaliente.pos.application.responses.productcatalogue;


import com.kaliente.pos.application.models.productcatalogue.ProductCatalogueDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetProductCatalogueByIdResponseDto {
	ProductCatalogueDetailsDto productCatalogue;
}
