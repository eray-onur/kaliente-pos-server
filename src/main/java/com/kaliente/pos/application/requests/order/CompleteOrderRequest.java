package com.kaliente.pos.application.requests.order;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompleteOrderRequest {
    private UUID orderId;
}
