package com.kaliente.pos.application.models.dtos.auth;

import java.util.UUID;

import com.kaliente.pos.application.models.base.BaseResponse;

public class RegisterPersonnelResponseDto extends BaseResponse<UUID> {
	private UUID registeredPersonnelId;
	private String registeredPersonnelEmail;

	public RegisterPersonnelResponseDto(UUID registeredPersonnelId, String registeredPersonnelEmail) {
		super(registeredPersonnelId);
		this.registeredPersonnelId = registeredPersonnelId;
		this.registeredPersonnelEmail = registeredPersonnelEmail;
	}

	public UUID getRegisteredPersonnelId() {
		return registeredPersonnelId;
	}

	public String getRegisteredPersonnelEmail() {
		return registeredPersonnelEmail;
	}
}
