package com.kaliente.pos.application.requests.order;

import com.kaliente.pos.domain.orderaggregate.OrderCustomerType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateCustomerInformationRequest {
    private UUID orderId;
    private String firstName;
    private String lastName;
    private String email;
    private OrderCustomerType customerType;
}
