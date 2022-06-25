package com.kaliente.pos.application.responses.administration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RemovePersonnelResponse {
    UUID removedPersonnelId;
    String removedPersonnelEmail;
}
