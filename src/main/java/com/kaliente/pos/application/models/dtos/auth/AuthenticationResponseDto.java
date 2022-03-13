package com.kaliente.pos.application.models.dtos.auth;

public class AuthenticationResponseDto {
	private final String jwt;

	public AuthenticationResponseDto(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
