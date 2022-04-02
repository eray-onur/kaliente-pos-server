package com.kaliente.pos.application.models.dtos.auth;

import java.util.UUID;


public class RegisterPersonnelResponseDto {
	private UUID registeredPersonnelId;
	private String registeredPersonnelEmail;

	public RegisterPersonnelResponseDto(UUID registeredPersonnelId, String registeredPersonnelEmail) {
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
