package com.kaliente.pos.domain.useraggregate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
	
	@Query("select u from application_user u where u.email = ?1")
	public ApplicationUser findByEmail(String email);
	
	@Query("select u from application_user u where u.userName = ?1")
	public ApplicationUser findByUserName(String userName);
	
}
