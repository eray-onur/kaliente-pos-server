package com.kaliente.pos.application.dtos.auth;

import java.util.UUID;

public class RegisterAdminResponseDto {
	private UUID createdAdminId;

	public RegisterAdminResponseDto(UUID createdAdminId) {
		super();
		this.createdAdminId = createdAdminId;
	}

	public UUID getCreatedAdminId() {
		return createdAdminId;
	}
}
