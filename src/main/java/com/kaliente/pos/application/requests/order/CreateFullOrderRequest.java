package com.kaliente.pos.application.requests.order;

import com.kaliente.pos.application.models.order.OrderCustomerDto;
import com.kaliente.pos.application.models.order.OrderPartialTransactionDto;
import com.kaliente.pos.application.models.order.OrderProductDto;
import lombok.Data;

import java.util.List;

@Data
public class CreateFullOrderRequest {
    private String currencyTitle;
    private List<OrderProductDto> orderedProducts;
    private OrderCustomerDto orderedBy;
    private List<OrderPartialTransactionDto> transactions;
}
