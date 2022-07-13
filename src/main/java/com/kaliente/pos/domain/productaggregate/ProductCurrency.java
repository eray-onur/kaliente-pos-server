package com.kaliente.pos.domain.productaggregate;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class ProductCurrency {
    private String currencyTitle;
    private double baseCrossRate;
    private double currencyRate;
    private Date currencyDate;
}