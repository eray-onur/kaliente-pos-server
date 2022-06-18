package com.kaliente.pos.application.requests.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerInformationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String customerType;
}
