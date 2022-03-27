package com.kaliente.pos.application.models.dtos.auth;

import com.kaliente.pos.application.models.base.BaseResponse;

public class AuthenticationResponseDto extends BaseResponse<String> {

	public AuthenticationResponseDto(String jwt) {
		super(jwt);
	}

}
