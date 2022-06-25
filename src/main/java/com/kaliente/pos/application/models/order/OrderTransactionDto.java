package com.kaliente.pos.application.models.order;

import com.kaliente.pos.domain.orderaggregate.OrderCurrency;
import com.kaliente.pos.domain.orderaggregate.TransactionMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderTransactionDto {
    private UUID orderTransactionId;
    private UUID belongingOrderId;
    private TransactionMethod paymentMethod;
    private OrderCurrency paymentCurrency;
    private double paidAmount;
}
