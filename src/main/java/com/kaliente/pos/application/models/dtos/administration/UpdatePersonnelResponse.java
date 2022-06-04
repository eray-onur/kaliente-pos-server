package com.kaliente.pos.application.models.dtos.administration;

import java.util.UUID;

public class UpdatePersonnelResponse {
    private UUID updatedPersonnelId;

    public UpdatePersonnelResponse() {}

    public UpdatePersonnelResponse(UUID updatedPersonnelId) {
        this.updatedPersonnelId = updatedPersonnelId;
    }

    public UUID getUpdatedPersonnelId() {
        return this.updatedPersonnelId;
    }
}
