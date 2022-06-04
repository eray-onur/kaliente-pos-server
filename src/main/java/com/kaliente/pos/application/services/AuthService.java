package com.kaliente.pos.application.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaliente.pos.domain.useraggregate.User;
import com.kaliente.pos.infrastructure.persistence.RoleHibernateRepository;
import com.kaliente.pos.infrastructure.persistence.UserHibernateRepository;
import com.kaliente.pos.application.models.dtos.auth.PersonnelDetailsDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterAdminRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterAdminResponseDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterPersonnelRequestDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterPersonnelResponseDto;
import com.kaliente.pos.application.models.dtos.auth.RegisterRequestDto;
import com.kaliente.pos.domain.useraggregate.Role;
import com.kaliente.pos.sharedkernel.util.JwtUtil;

@Service
public class AuthService {
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private UserHibernateRepository userRepository;
	
	@Autowired
	private RoleHibernateRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	

    @Autowired
    private ModelMapper modelMapper;
    
    public List<PersonnelDetailsDto> getPersonnelList() {
    	Collection<User> foundPersonnel = userRepository.findAllUsers();
    	
    	List<PersonnelDetailsDto> personnelList = foundPersonnel.stream().map(element -> modelMapper.map(element, PersonnelDetailsDto.class)).collect(Collectors.toList());
    	
    	return personnelList;
    }
	
	
	public String register(RegisterRequestDto registerDto) {
		
		User userToCreate = modelMapper.map(registerDto, User.class);
		userToCreate.setRoles(Arrays.asList(roleRepository.findByTitle("ROLE_PERSONNEL")));
		User createdUser = this.userRepository.save(userToCreate);
		
		if(createdUser == null) {
			throw new Error("Could not create character.");
		}
		
		UserDetails newUserDetail = userRepository.loadUserByUsername(
				createdUser.getEmail()
		);
		final String jToken = jwtTokenUtil.generateToken(newUserDetail);
		
		return jToken;
	}
	
	@Transactional
	public RegisterAdminResponseDto registerAdmin(RegisterAdminRequestDto dto) {
		User adminToRegister = modelMapper.map(dto, User.class);
		adminToRegister.setPassword(
			passwordEncoder.encode(adminToRegister.getPassword())
		);

		Role adminRole = this.roleRepository.findByTitle("ROLE_ADMIN");
		adminToRegister.getRoles().add(adminRole);
		adminRole.getUsers().add(adminToRegister);

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
		personnelToRegister.getRoles().add(personnelRole);
		personnelRole.getUsers().add(personnelToRegister);


		User registeredUser = userRepository.save(personnelToRegister);

		return new RegisterPersonnelResponseDto(registeredUser.getId(), registeredUser.getEmail());
	}
	
	
}
