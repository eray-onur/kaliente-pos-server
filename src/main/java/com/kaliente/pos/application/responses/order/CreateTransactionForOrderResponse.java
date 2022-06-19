package com.kaliente.pos.application.responses.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CreateTransactionForOrderResponse {
    private UUID transactionOrderId;
}