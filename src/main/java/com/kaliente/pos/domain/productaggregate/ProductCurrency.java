package com.kaliente.pos.domain.productaggregate;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
@Getter
@Setter
public class ProductCurrency {
    private String currencyTitle;
    private double baseCrossRate;
}