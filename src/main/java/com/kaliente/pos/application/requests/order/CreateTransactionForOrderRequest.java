package com.kaliente.pos.application.requests.order;

import com.kaliente.pos.domain.orderaggregate.TransactionMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTransactionForOrderRequest {

    private UUID orderId;
    private String paymentCurrencyTitle;
    private TransactionMethod paymentMethod;
    private double paidAmount;
}
