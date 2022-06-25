package com.kaliente.pos.application.models.order;

import com.kaliente.pos.domain.orderaggregate.TransactionMethod;
import lombok.Data;

@Data
public class OrderPartialTransactionDto {
    private TransactionMethod paymentMethod;
    private String paymentCurrencyTitle;
    private double paidAmount;
}
