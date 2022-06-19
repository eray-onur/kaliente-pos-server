package com.kaliente.pos.application.requests.order;

import com.kaliente.pos.domain.orderaggregate.OrderPaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTransactionForOrderRequest {

    private UUID orderId;
    private String paymentCurrencyTitle;
    private OrderPaymentMethod paymentMethod;
    private double paidAmount;
}
