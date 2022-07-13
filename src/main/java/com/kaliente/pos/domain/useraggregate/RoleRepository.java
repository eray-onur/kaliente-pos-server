package com.kaliente.pos.domain.useraggregate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
	Role findByTitle(String title);
}
