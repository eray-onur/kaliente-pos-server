package com.kaliente.pos.application.models.productcatalogue;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductCatalogueDetailsDto {
	private UUID id;
	private String title;
	private String description;
	private UUID parentCatalogueId;
}
