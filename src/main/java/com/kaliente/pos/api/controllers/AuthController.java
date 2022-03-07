package com.kaliente.pos.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaliente.pos.application.dtos.auth.AuthenticationRequestDto;
import com.kaliente.pos.application.dtos.auth.AuthenticationResponseDto;
import com.kaliente.pos.application.dtos.auth.RegisterRequestDto;
import com.kaliente.pos.application.dtos.auth.RegisterResponseDto;
import com.kaliente.pos.application.services.AuthService;
import com.kaliente.pos.sharedkernel.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private AuthService authService;
	

	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto requestDto) throws Exception {
		final Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(),
                        requestDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
		final String jToken = jwtTokenUtil.generateToken(authentication);
		
		return ResponseEntity.ok(new AuthenticationResponseDto(jToken));
	
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto requestDto) {
		String token = authService.register(requestDto);
		return ResponseEntity.ok(new RegisterResponseDto(token));
	}
	
}
