package com.kaliente.pos.application.models.dtos.auth;

import java.util.List;


public class GetPersonnelListResponseDto {
	private List<PersonnelDetailsDto> foundPersonnel;
	

	public List<PersonnelDetailsDto> getFoundPersonnel() {
		return foundPersonnel;
	}

	public GetPersonnelListResponseDto(List<PersonnelDetailsDto> foundPersonnel) {
		this.foundPersonnel = foundPersonnel;
	}
}
