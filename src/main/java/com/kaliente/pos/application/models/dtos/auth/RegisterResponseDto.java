package com.kaliente.pos.application.models.dtos.auth;


public class RegisterResponseDto {
	private String jwt;

	public RegisterResponseDto(String jwt) {
		this.jwt = jwt;
	}

	public String getToken() {
		return jwt;
	}

	public void setToken(String jwt) {
		this.jwt = jwt;
	}
	
	
}
