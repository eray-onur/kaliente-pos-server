package com.kaliente.pos.domain.useraggregate;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserRepository extends UserDetailsService, JpaRepository<User, UUID> {
	
	public User findByEmail(String email);

	@Override
	public default UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User foundUser = findByEmail(email);

		if (foundUser == null) {
			throw new UsernameNotFoundException("Invalid email/password.");
		}

		return new org.springframework.security.core.userdetails.User(foundUser.getEmail(), foundUser.getPassword(),
				!foundUser.isDeleted(), true, true, true, getAuthority(foundUser));
	}

	public default Set<SimpleGrantedAuthority> getAuthority(User foundUser) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(foundUser.getRole().getTitle()));
		// foundUser.getRoles().forEach(role -> {
		//     authorities.add(new SimpleGrantedAuthority(role.getTitle()));
		// });
		return authorities;
	}
	
}
