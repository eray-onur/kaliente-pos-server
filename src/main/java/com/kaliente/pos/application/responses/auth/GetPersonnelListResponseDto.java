package com.kaliente.pos.application.responses.auth;

import com.kaliente.pos.application.models.auth.PersonnelDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPersonnelListResponseDto {
	private List<PersonnelDetailsDto> foundPersonnel;
}
