package com.kaliente.pos.domain.orderaggregate;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
@Getter
@Setter
public class OrderCurrency {
    private String currencyTitle;
    private double baseCrossRate;
    private Date currencyDate;
    private double currencyRate;
}
