package com.kaliente.pos.application.responses.administration;

import java.util.UUID;

public class RemovePersonnelResponse {
    UUID removedPersonnelId;
    String removedPersonnelEmail;

    public RemovePersonnelResponse(UUID removedPersonnelId, String removedPersonnelEmail) {
        this.removedPersonnelId = removedPersonnelId;
        this.removedPersonnelEmail = removedPersonnelEmail;
    }

    public RemovePersonnelResponse(UUID id) {
        removedPersonnelId = id;
    }

    public UUID getRemovedPersonnelId() {
        return this.removedPersonnelId;
    }

    public void setRemovedPersonnelId(UUID value) {
        this.removedPersonnelId = value;
    }

    public String getRemovedPersonnelEmail() {
        return this.removedPersonnelEmail;
    }

    public void setRemovedPersonnelEmail(String value) {
        this.removedPersonnelEmail = value;
    }
}
