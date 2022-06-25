package com.kaliente.pos.application.responses.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class RegisterAdminResponseDto {
	private UUID registeredAdminId;
	private String registeredAdminEmail;
	
}
