package com.kaliente.pos.application.requests.product;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductUpdateRequestDto {
	private UUID id;
	private String title;
	private String description;
	private double stockedUnits;
	private String currencyTitle;
	private double cost;
	private double price;
	private UUID catalogueId;

}
