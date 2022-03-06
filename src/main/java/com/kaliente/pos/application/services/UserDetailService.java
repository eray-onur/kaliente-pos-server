package com.kaliente.pos.application.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kaliente.pos.domain.useraggregate.ApplicationUser;
import com.kaliente.pos.domain.useraggregate.ApplicationUserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private ApplicationUserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		ApplicationUser foundUser = userRepository.findByEmail(email);
		
		if(foundUser == null) {
			throw new UsernameNotFoundException("Invalid email/password.");
		}
		
		return new User(foundUser.getEmail(), foundUser.getPassword(), foundUser.isActive(), true, true, true, getAuthority(foundUser));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(ApplicationUser user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority(role.getTitle()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
}
