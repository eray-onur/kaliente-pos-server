package com.kaliente.pos.application.dtos.auth;

import java.util.List;

public class GetPersonnelListResponseDto {
	private List<PersonnelDetailsDto> foundPersonnel;

	public List<PersonnelDetailsDto> getFoundPersonnel() {
		return foundPersonnel;
	}

	public GetPersonnelListResponseDto(List<PersonnelDetailsDto> foundPersonnel) {
		super();
		this.foundPersonnel = foundPersonnel;
	}
}
