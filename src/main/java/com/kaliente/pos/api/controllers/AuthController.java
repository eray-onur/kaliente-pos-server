package com.kaliente.pos.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaliente.pos.application.models.dtos.auth.AuthenticationRequestDto;
import com.kaliente.pos.application.models.dtos.auth.AuthenticationResponseDto;
import com.kaliente.pos.application.models.dtos.auth.GetPersonnelListResponseDto;
import com.kaliente.pos.application.models.dtos.auth.PersonnelDetailsDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterAdminRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterAdminResponseDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterPersonnelRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterPersonnelResponseDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterResponseDto;
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
	

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/getPersonnelList")
	public ResponseEntity<GetPersonnelListResponseDto> getPersonnelList() {
		List<PersonnelDetailsDto> personnelList = authService.getPersonnelList();
		return ResponseEntity.ok(new GetPersonnelListResponseDto(personnelList));
	
	}
	
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
	
	@GetMapping(value="health_check")
	public ResponseEntity<String> health_check() throws Exception {
		return ResponseEntity.ok("Things are looking good!");
	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
	@PostMapping("/registerAdmin")
	public ResponseEntity<RegisterAdminResponseDto> registerAdmin(@RequestBody RegisterAdminRequestDto requestDto) {
		var response = authService.registerAdmin(requestDto);
		return ResponseEntity.ok(response);
	}
	

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/registerPersonnel")
	public ResponseEntity<RegisterPersonnelResponseDto> registerPersonnel(@RequestBody RegisterPersonnelRequestDto requestDto) {
		var registeredPersonnel = authService.registerPersonnel(requestDto);
		return ResponseEntity.ok(registeredPersonnel);
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto requestDto) {
		String token = authService.register(requestDto);
		return ResponseEntity.ok(new RegisterResponseDto(token));
	}
	
}
