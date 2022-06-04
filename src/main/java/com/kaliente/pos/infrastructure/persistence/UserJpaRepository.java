package com.kaliente.pos.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaliente.pos.domain.useraggregate.User;

public interface UserJpaRepository extends JpaRepository<User, UUID>  {
    
}
