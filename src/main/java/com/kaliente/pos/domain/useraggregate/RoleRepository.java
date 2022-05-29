package com.kaliente.pos.domain.useraggregate;

import java.util.ArrayList;

public interface RoleRepository {
	Role findByTitle(String title);

	ArrayList<Role> findAllSystemRoles();
}
