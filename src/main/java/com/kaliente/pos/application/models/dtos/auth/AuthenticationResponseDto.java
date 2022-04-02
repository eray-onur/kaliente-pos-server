package com.kaliente.pos.application.models.dtos.auth;


public class AuthenticationResponseDto {

	String jwt;

	public AuthenticationResponseDto(String jwt) {
		this.jwt = jwt;	
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	
}
