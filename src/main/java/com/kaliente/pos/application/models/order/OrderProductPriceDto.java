package com.kaliente.pos.application.models.order;

import com.kaliente.pos.domain.orderaggregate.OrderCurrency;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderProductPriceDto {
    private UUID orderProductId;
    private UUID belongingProductId;
    private OrderCurrency currency;
}
