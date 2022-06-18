package com.kaliente.pos.domain.productaggregate;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@AllArgsConstructor
@Embeddable
@Builder
@Getter
@Setter
public class ProductCurrency {
    private String currencyTitle;
    private double baseCrossRate;
    private double currencyRate;
    private Date currencyDate;

    public ProductCurrency() {
        currencyDate = new Date();
    }

}