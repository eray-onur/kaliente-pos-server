package com.kaliente.pos.domain.productaggregate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCatalogueRepository extends JpaRepository<ProductCatalogue, UUID> {
	
}
