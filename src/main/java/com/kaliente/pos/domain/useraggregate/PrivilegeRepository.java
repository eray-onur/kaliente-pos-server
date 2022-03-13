package com.kaliente.pos.domain.useraggregate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {

	@Query("select p from privileges p where p.title = ?1")
	Privilege findByTitle(String title);
}
