package com.kaliente.pos.application.responses.product;


import com.kaliente.pos.application.models.product.ProductDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetProductByIdResponseDto {
	ProductDetailsDto foundProduct;
}
