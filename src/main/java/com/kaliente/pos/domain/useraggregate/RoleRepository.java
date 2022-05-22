package com.kaliente.pos.domain.useraggregate;


public interface RoleRepository {
	Role findByTitle(String title);
}
