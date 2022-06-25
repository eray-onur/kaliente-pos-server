package com.kaliente.pos.application.requests.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdminRequestDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
