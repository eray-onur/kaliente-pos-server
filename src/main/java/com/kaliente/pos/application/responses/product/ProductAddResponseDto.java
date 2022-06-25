package com.kaliente.pos.application.responses.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProductAddResponseDto {
	UUID addedProductId;
	String addedProductTitle;
}
