package com.kaliente.pos.domain.productaggregate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Modifying
    @Query("update products p set p.imagePath = ?1 where p.id = ?2")
    void setImagePathById(String imagePath, UUID productId);

}
