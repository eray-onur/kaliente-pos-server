package com.kaliente.pos.application.models.dtos.auth;

import java.util.UUID;



public class RegisterAdminResponseDto {
	private UUID registeredAdminId;
	private String registeredAdminEmail;

	public RegisterAdminResponseDto(UUID registeredAdminId, String registeredAdminEmail) {
		
		this.registeredAdminId = registeredAdminId;
		this.registeredAdminEmail = registeredAdminEmail;
	}

	public UUID getRegisteredAdminId() {
		return registeredAdminId;
	}

	public String getRegisteredAdminEmail() {
		return registeredAdminEmail;
	}
	
}
