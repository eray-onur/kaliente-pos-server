package com.kaliente.pos.application.responses.order;

import com.kaliente.pos.application.models.order.OrderDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetOrdersResponse {
    private List<OrderDetailsDto> foundOrders;
}
