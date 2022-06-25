package com.kaliente.pos.application.responses.administration;

import com.kaliente.pos.application.models.auth.PersonnelDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetPersonnelByIdResponse {
    PersonnelDetailsDto foundPersonnel;
}
