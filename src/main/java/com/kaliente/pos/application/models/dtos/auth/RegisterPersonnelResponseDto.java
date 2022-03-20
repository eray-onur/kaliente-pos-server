package com.kaliente.pos.application.models.dtos.auth;

import java.util.UUID;

import com.kaliente.pos.application.models.base.BaseResponse;

public class RegisterPersonnelResponseDto extends BaseResponse<UUID> {
	private UUID registeredPersonnelId;

	public RegisterPersonnelResponseDto(UUID registeredPersonnelId) {
		super(registeredPersonnelId);
		this.registeredPersonnelId = registeredPersonnelId;
	}

	public UUID getCreatedPersonnelId() {
		return registeredPersonnelId;
	}
}
