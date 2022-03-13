package com.kaliente.pos.domain.useraggregate;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserRepository extends JpaRepository<User, UUID>, UserDetailsService {
	
	@Query("select u from users u where u.email = ?1")
	public User findByEmail(String email);
	
	@Query("select u from users u where u.userName = ?1")
	public User findByUserName(String userName);
	
	@Override
	public default UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User foundUser = findByEmail(email);
		
		if(foundUser == null) {
			throw new UsernameNotFoundException("Invalid email/password.");
		}
		
		return new org.springframework.security.core.userdetails.User(foundUser.getEmail(), foundUser.getPassword(), foundUser.isActive(), true, true, true, getAuthority(foundUser));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority(role.getTitle()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
}
