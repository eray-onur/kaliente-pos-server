package com.kaliente.pos.application.requests.currency;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class AppendNewCurrencyRequest {
    private String currencyTitle;
    private double baseCrossRate;
    private double currencyRate;
}
