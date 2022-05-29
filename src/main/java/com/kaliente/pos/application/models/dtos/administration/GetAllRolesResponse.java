package com.kaliente.pos.application.models.dtos.administration;

public class GetAllRolesResponse {

    String title;

    public GetAllRolesResponse(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
