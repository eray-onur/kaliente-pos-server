package com.kaliente.pos.domain.useraggregate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, UUID> {
	
	@Query("select u from users u where u.email = ?1")
	public User findByEmail(String email);
	
	@Query("select u from users u where u.userName = ?1")
	public User findByUserName(String userName);
	
}
