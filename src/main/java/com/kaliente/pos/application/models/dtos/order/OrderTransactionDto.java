package com.kaliente.pos.application.models.dtos.order;

import com.kaliente.pos.domain.orderaggregate.OrderCurrency;
import com.kaliente.pos.domain.orderaggregate.OrderPaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderTransactionDto {
    private UUID orderTransactionId;
    private UUID belongingOrderId;
    private OrderPaymentMethod paymentMethod;
    private OrderCurrency paymentCurrency;
    private double paidAmount;
}
