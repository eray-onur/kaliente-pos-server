package com.kaliente.pos.application.requests.administration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePersonnelRequest {
    private UUID personnelId;
    private String firstName;
    private String lastName;
}
