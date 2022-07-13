package com.kaliente.pos.domain.useraggregate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {

	Privilege findByTitle(String title);
}
