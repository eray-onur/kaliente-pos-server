package com.kaliente.pos.application.requests.order;

import com.kaliente.pos.application.models.dtos.order.OrderCustomerDto;
import com.kaliente.pos.application.models.dtos.order.OrderProductDto;
import lombok.Data;

import java.util.List;

@Data
public class CreatePartialOrderRequest {
    private String currencyTitle;
    private List<OrderProductDto> orderedProducts;
    private OrderCustomerDto orderedBy;
}
