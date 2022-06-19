package com.kaliente.pos.application.responses.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CompleteOrderResponse {
    private UUID completedOrderId;
}