package com.kaliente.pos.application.responses.order;

import com.kaliente.pos.application.models.dtos.order.OrderDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderByCustomerResponse {
    private OrderDetailsDto foundOrder;
}