package com.kaliente.pos.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExampleRepository extends JpaRepository<Example, UUID> {
}
