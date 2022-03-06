package com.kaliente.pos.domain.useraggregate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, UUID> {

	@Query("select r from role r where r.title = ?1")
	Role findByTitle(String title);
}
