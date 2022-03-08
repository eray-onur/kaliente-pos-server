package com.kaliente.pos.application.dtos.auth;

import java.util.UUID;

public class RegisterPersonnelResponseDto {
	private UUID createdPersonnelId;

	public RegisterPersonnelResponseDto(UUID createdPersonnelId) {
		super();
		this.createdPersonnelId = createdPersonnelId;
	}

	public UUID getCreatedPersonnelId() {
		return createdPersonnelId;
	}
}
