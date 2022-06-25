package com.kaliente.pos.application.responses.productcatalogue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class ProductCatalogueAddResponseDto {
	UUID addedCatalogueId;
	String addedCatalogueTitle;
}
