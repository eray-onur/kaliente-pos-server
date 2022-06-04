package com.kaliente.pos.application.models.dtos.administration;

import java.util.UUID;

public class UpdatePersonnelRequest {
    private UUID personnelId;
    private String firstName;
    private String lastName;

    public UpdatePersonnelRequest() {}

    public UpdatePersonnelRequest(UUID personnelId, String firstName, String lastName) {
    }


    public UUID getPersonnelId() {
        return this.personnelId;
    }

    public void setPersonnelId(UUID personnelId) {
        this.personnelId = personnelId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
