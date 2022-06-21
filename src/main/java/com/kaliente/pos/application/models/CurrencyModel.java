package com.kaliente.pos.application.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CurrencyModel {
    private String currencyTitle;
    private double baseCrossRate;
    private double currencyRate;
    private Date currencyDate;
}
