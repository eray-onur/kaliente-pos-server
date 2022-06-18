package com.kaliente.pos.domain.productaggregate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface ProductCatalogueRepository extends JpaRepository<ProductCatalogue, UUID> {
}
