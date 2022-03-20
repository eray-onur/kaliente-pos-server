package com.kaliente.pos.application.models.dtos.auth;

import java.util.List;

import com.kaliente.pos.application.models.base.BaseResponse;

public class GetPersonnelListResponseDto extends BaseResponse<List<PersonnelDetailsDto>> {
	private List<PersonnelDetailsDto> foundPersonnel;
	

	public List<PersonnelDetailsDto> getFoundPersonnel() {
		return foundPersonnel;
	}

	public GetPersonnelListResponseDto(List<PersonnelDetailsDto> foundPersonnel) {
		super(foundPersonnel);
		this.foundPersonnel = foundPersonnel;
	}
}
