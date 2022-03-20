package com.kaliente.pos.application.models.dtos.auth;

import com.kaliente.pos.application.models.base.BaseResponse;

public class AuthenticationResponseDto extends BaseResponse<String> {
	private final String jwt;

	public AuthenticationResponseDto(String jwt) {
		super(jwt);
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
