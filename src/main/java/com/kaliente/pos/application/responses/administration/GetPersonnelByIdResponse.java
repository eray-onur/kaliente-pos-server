package com.kaliente.pos.application.responses.administration;

import com.kaliente.pos.application.models.dtos.auth.PersonnelDetailsDto;

public class GetPersonnelByIdResponse {
    PersonnelDetailsDto foundPersonnel;

    public GetPersonnelByIdResponse(PersonnelDetailsDto foundPersonnel) {
        this.foundPersonnel = foundPersonnel;
    }


    public PersonnelDetailsDto getFoundPersonnel() {
        return foundPersonnel;
    }

    public void setFoundPersonnel(PersonnelDetailsDto foundPersonnel) {
        this.foundPersonnel = foundPersonnel;
    }
}
