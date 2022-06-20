package com.kaliente.pos.application.requests.order;

import com.kaliente.pos.application.models.dtos.order.OrderCustomerDto;
import com.kaliente.pos.application.models.dtos.order.OrderPartialTransactionDto;
import com.kaliente.pos.application.models.dtos.order.OrderProductDto;
import com.kaliente.pos.application.models.dtos.order.OrderTransactionDto;
import lombok.Data;

import java.util.List;

@Data
public class CreateFullOrderRequest {
    private String currencyTitle;
    private List<OrderProductDto> orderedProducts;
    private OrderCustomerDto orderedBy;
    private List<OrderPartialTransactionDto> transactions;
}
