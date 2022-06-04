package com.kaliente.pos.application.models.dtos.administration;

import java.util.ArrayList;
import java.util.List;

public class GetAllRolesResponse {

    ArrayList<String> titles;

    public GetAllRolesResponse(List<String> titles) {
        this.titles = new ArrayList<String>(titles);
    }

    public ArrayList<String> getTitle() {
        return titles;
    }
}
