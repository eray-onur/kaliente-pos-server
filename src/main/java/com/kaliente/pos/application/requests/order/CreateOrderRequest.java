package com.kaliente.pos.application.requests.order;

import com.kaliente.pos.application.models.CurrencyDto;
import com.kaliente.pos.application.models.dtos.order.OrderCustomerDto;
import com.kaliente.pos.application.models.dtos.order.OrderProductDto;
import com.kaliente.pos.domain.currency.CurrencyHistory;
import com.kaliente.pos.domain.orderaggregate.OrderCurrency;
import com.kaliente.pos.domain.productaggregate.ProductCurrency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderRequest {
    private String currencyTitle;
    private List<OrderProductDto> orderedProducts;
    private OrderCustomerDto orderedBy;
}
