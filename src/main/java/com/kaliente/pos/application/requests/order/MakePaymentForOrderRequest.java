package com.kaliente.pos.application.requests.order;

import com.kaliente.pos.domain.orderaggregate.OrderPaymentMethod;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MakePaymentForOrderRequest {

    private UUID orderId;
    private String paymentCurrency;
    private OrderPaymentMethod paymentMethod;
    private double amount;
}
