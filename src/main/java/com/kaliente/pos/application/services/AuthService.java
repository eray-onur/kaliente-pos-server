package com.kaliente.pos.application.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.dtos.auth.PersonnelDetailsDto;
import com.kaliente.pos.application.dtos.auth.RegisterAdminRequestDto;
import com.kaliente.pos.application.dtos.auth.RegisterAdminResponseDto;
import com.kaliente.pos.application.dtos.auth.RegisterPersonnelRequestDto;
import com.kaliente.pos.application.dtos.auth.RegisterPersonnelResponseDto;
import com.kaliente.pos.application.dtos.auth.RegisterRequestDto;
import com.kaliente.pos.domain.useraggregate.ApplicationUser;
import com.kaliente.pos.domain.useraggregate.ApplicationUserRepository;
import com.kaliente.pos.domain.useraggregate.RoleRepository;
import com.kaliente.pos.sharedkernel.util.JwtUtil;

@Service
public class AuthService {

	
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
    
    public List<PersonnelDetailsDto> getPersonnelList() {
    	List<ApplicationUser> foundPersonnel = userRepository.findAll();
    	
    	List<PersonnelDetailsDto> personnelList = foundPersonnel.stream().map(element -> modelMapper.map(element, PersonnelDetailsDto.class)).collect(Collectors.toList());
    	
    	return personnelList;
    }
	
	
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
	
	public RegisterAdminResponseDto registerAdmin(RegisterAdminRequestDto dto) {
		ApplicationUser adminToRegister = modelMapper.map(dto, ApplicationUser.class);
		ApplicationUser registeredAdmin = userRepository.save(adminToRegister);
		return new RegisterAdminResponseDto(registeredAdmin.getId());
	}
	
	public RegisterPersonnelResponseDto registerPersonnel(RegisterPersonnelRequestDto dto) {
		ApplicationUser userToRegister = modelMapper.map(dto, ApplicationUser.class);
		ApplicationUser registeredUser = userRepository.save(userToRegister);
		return new RegisterPersonnelResponseDto(registeredUser.getId());
	}
	
	
}
