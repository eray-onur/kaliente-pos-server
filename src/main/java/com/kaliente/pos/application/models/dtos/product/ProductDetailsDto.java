package com.kaliente.pos.application.models.dtos.product;

import java.util.UUID;

import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.domain.productaggregate.ProductCurrency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailsDto {
	private UUID id;
	private String title;
	private String description;
	private double cost;
	private double price;
	private ProductCurrency currency;
	private ProductCatalogueDetailsDto catalogue;

}
