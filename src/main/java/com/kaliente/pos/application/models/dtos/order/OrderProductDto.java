package com.kaliente.pos.application.models.dtos.order;

import com.kaliente.pos.domain.orderaggregate.OrderCurrency;
import com.kaliente.pos.domain.productaggregate.ProductCurrency;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderProductDto {
    private UUID orderId;
    private UUID orderedProductId;
    private String orderedProductTitle;
    private ProductCurrency currency;
    private double price;
    private double invoicePath;
}
