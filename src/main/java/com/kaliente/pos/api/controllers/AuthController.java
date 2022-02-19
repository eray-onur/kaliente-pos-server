package com.kaliente.pos.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaliente.pos.application.dtos.auth.AuthenticationRequestDto;
import com.kaliente.pos.application.dtos.auth.AuthenticationResponseDto;
import com.kaliente.pos.application.services.UserDetailService;
import com.kaliente.pos.sharedkernel.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto requestDto) throws Exception {
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword())
			);
		} catch (BadCredentialsException ex) {
			throw new Exception("Incorrect username or password.");
		}
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(requestDto.getUsername());
		final String jToken = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponseDto(jToken));
	
	}
	
}
