package com.kaliente.pos.domain.useraggregate;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserRepository {
	
	User findByEmail(String email);

	public Collection<User> findAllUsers();
	
	public User findByUserName(String userName);
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
	
	// public Set<SimpleGrantedAuthority> getAuthority(User user);

	public User save(User user);
	
}
