package com.kaliente.pos.application.responses.administration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePersonnelResponse {
    private UUID updatedPersonnelId;
}
