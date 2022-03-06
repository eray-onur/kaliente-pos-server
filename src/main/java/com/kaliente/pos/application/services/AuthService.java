package com.kaliente.pos.application.services;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.dtos.auth.RegisterRequestDto;
import com.kaliente.pos.domain.useraggregate.ApplicationUser;
import com.kaliente.pos.domain.useraggregate.ApplicationUserRepository;
import com.kaliente.pos.domain.useraggregate.RoleRepository;
import com.kaliente.pos.sharedkernel.util.JwtUtil;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private ApplicationUserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	

    @Autowired
    private ModelMapper modelMapper;
	
	
	public String register(RegisterRequestDto registerDto) {
		
		ApplicationUser userToCreate = modelMapper.map(registerDto, ApplicationUser.class);
		userToCreate.setRoles(Arrays.asList(roleRepository.findByTitle("ROLE_PERSONNEL")));
		ApplicationUser createdUser = this.userRepository.save(userToCreate);
		
		if(createdUser == null) {
			throw new Error("Could not create character.");
		}
		
		UserDetails newUserDetail = userDetailService.loadUserByUsername(
				createdUser.getEmail()
		);
		final String jToken = jwtTokenUtil.generateToken(newUserDetail);
		
		return jToken;
	}
	
	
}
