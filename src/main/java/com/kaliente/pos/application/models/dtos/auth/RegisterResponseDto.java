package com.kaliente.pos.application.models.dtos.auth;

import com.kaliente.pos.application.models.base.BaseResponse;

public class RegisterResponseDto extends BaseResponse<String> {
	private String jwt;

	public RegisterResponseDto(String jwt) {
		super(jwt);
		this.jwt = jwt;
	}

	public String getToken() {
		return jwt;
	}

	public void setToken(String jwt) {
		this.jwt = jwt;
	}
	
	
}
