package com.kaliente.pos.application.models.dtos.order;

import com.kaliente.pos.domain.orderaggregate.OrderCustomerType;
import com.kaliente.pos.domain.orderaggregate.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDetailsDto {
    private OrderCustomerDto orderedBy;
    private Date orderingDate;
    private Date completionDate;
    private OrderStatus status;
    private List<OrderProductDto> orderProducts;
    private List<OrderTransactionDto> paymentTransactions;
}
