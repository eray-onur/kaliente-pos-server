package com.kaliente.pos.application.models.dtos.auth;

import java.util.UUID;

import com.kaliente.pos.application.models.base.BaseResponse;

public class RegisterAdminResponseDto extends BaseResponse<UUID> {
	private UUID registeredAdminId;

	public RegisterAdminResponseDto(UUID registeredAdminId) {
		super(registeredAdminId);
		this.registeredAdminId = registeredAdminId;
	}

	public UUID getCreatedAdminId() {
		return registeredAdminId;
	}
}
