package com.kaliente.pos.domain.useraggregate;

public interface PrivilegeRepository  {

	Privilege findByTitle(String title);
}
