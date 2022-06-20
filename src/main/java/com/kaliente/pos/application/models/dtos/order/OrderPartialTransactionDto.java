package com.kaliente.pos.application.models.dtos.order;

import com.kaliente.pos.domain.orderaggregate.OrderCurrency;
import com.kaliente.pos.domain.orderaggregate.OrderPaymentMethod;
import lombok.Data;

@Data
public class OrderPartialTransactionDto {
    private OrderPaymentMethod paymentMethod;
    private String paymentCurrencyTitle;
    private double paidAmount;
}
