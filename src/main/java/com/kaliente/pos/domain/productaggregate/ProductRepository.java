package com.kaliente.pos.domain.productaggregate;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, UUID> {
		
	@Transactional
	@Modifying 
	@Query("UPDATE products p SET p.title = :title, p.description = :description, p.price = :price where p.id = :id")
	void updateProduct(UUID id, String title, String description, double price);
}
