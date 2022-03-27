package com.kaliente.pos.domain.productaggregate;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductCatalogueRepository extends JpaRepository<ProductCatalogue, UUID> {
	@Transactional
	@Modifying 
	@Query("UPDATE product_catalogues pc SET pc.title = :title, pc.description = :description where pc.id = :id")
	void updateCatalogue(UUID id, String title, String description);
}
