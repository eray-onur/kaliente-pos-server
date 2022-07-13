package com.kaliente.pos.application.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.kaliente.pos.domain.useraggregate.RoleRepository;
import com.kaliente.pos.domain.useraggregate.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaliente.pos.domain.useraggregate.User;
import com.kaliente.pos.application.models.auth.PersonnelDetailsDto;
import com.kaliente.pos.application.requests.auth.RegisterAdminRequestDto;
import com.kaliente.pos.application.responses.auth.RegisterAdminResponseDto;
import com.kaliente.pos.application.requests.auth.RegisterPersonnelRequestDto;
import com.kaliente.pos.application.responses.auth.RegisterPersonnelResponseDto;
import com.kaliente.pos.application.requests.auth.RegisterRequestDto;
import com.kaliente.pos.domain.useraggregate.Role;
import com.kaliente.pos.application.utilities.JwtUtility;

@Service
public class AuthService {
	private final JwtUtility jwtUtility;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

	@Autowired
	public AuthService(JwtUtility jwtUtility, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
		this.jwtUtility = jwtUtility;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
	}
    
    public List<PersonnelDetailsDto> getPersonnelList() {
    	Collection<User> foundPersonnel = userRepository.findAll();

		return foundPersonnel.stream().map(element -> modelMapper.map(element, PersonnelDetailsDto.class)).collect(Collectors.toList());
    }
	
	
	public String register(RegisterRequestDto registerDto) {
		
		User userToCreate = modelMapper.map(registerDto, User.class);
		userToCreate.setRole(roleRepository.findByTitle("ROLE_PERSONNEL"));
		User createdUser = this.userRepository.save(userToCreate);

		UserDetails newUserDetail = userRepository.loadUserByUsername(
				createdUser.getEmail()
		);

		return jwtUtility.generateToken(newUserDetail);
	}
	
	@Transactional
	public RegisterAdminResponseDto registerAdmin(RegisterAdminRequestDto dto) {
		User adminToRegister = modelMapper.map(dto, User.class);
		adminToRegister.setPassword(
			passwordEncoder.encode(adminToRegister.getPassword())
		);

		Role adminRole = this.roleRepository.findByTitle("ROLE_ADMIN");
		adminToRegister.setRole(adminRole);
		adminToRegister.setPersonnel(null);

		User registeredAdmin = userRepository.save(adminToRegister);
		return new RegisterAdminResponseDto(registeredAdmin.getId(), registeredAdmin.getEmail());
	}
	
	@Transactional
	public RegisterPersonnelResponseDto registerPersonnel(RegisterPersonnelRequestDto dto) {
		User personnelToRegister = modelMapper.map(dto, User.class);
		personnelToRegister.setPassword(
			passwordEncoder.encode(personnelToRegister.getPassword())
		);

		Role personnelRole = this.roleRepository.findByTitle("ROLE_PERSONNEL");
		personnelToRegister.setRole(personnelRole);
		personnelToRegister.setPersonnel(null);
		User registeredUser = userRepository.save(personnelToRegister);

		return new RegisterPersonnelResponseDto(registeredUser.getId(), registeredUser.getEmail());
	}
	
	
}
