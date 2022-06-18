package com.kaliente.pos.application.models.dtos.product;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductAddRequestDto {
	private String title;
	private String description;
	private double stockedUnits;
	private String currencyTitle;
	private double cost;
	private double price;
	private UUID catalogueId;
}
