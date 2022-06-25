package com.kaliente.pos.application.requests.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RegisterPersonnelRequestDto {
	private UUID roleId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
