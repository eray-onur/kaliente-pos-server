package com.kaliente.pos.application.models.order;

import com.kaliente.pos.domain.orderaggregate.OrderCustomerType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Getter
@Setter
public class OrderCustomerDto {
    private String firstName;
    private String lastName;
    private String email;
//    private String customerType;
}
