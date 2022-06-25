package com.kaliente.pos.application.requests.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequestDto {
	private String email;
	private String username;
	private String password;
}
