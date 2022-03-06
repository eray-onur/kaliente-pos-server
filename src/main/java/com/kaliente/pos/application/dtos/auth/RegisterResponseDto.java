package com.kaliente.pos.application.dtos.auth;

public class RegisterResponseDto {
	private String token;

	public RegisterResponseDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
