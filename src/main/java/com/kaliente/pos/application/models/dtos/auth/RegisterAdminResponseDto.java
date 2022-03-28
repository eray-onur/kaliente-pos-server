package com.kaliente.pos.application.models.dtos.auth;

import java.util.UUID;

import com.kaliente.pos.application.models.base.BaseResponse;

public class RegisterAdminResponseDto extends BaseResponse<UUID> {
	private UUID registeredAdminId;
	private String registeredAdminEmail;

	public RegisterAdminResponseDto(UUID registeredAdminId, String registeredAdminEmail) {
		super(registeredAdminId);
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
