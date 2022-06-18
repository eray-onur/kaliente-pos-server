package com.kaliente.pos.domain.productaggregate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {


    @Override
    @Query("select p from products p left join fetch p.catalogue where p.isActive = true")
    List<Product> findAll();

    @Override
    @Query("select p from products p left join fetch p.catalogue where p.isActive = true and p.id = ?1")
    Optional<Product> findById(UUID id);

    @Modifying
    @Query("update products p set isActive = false where p.id=?1")
    void deleteById(UUID id);

    @Modifying
    @Query("update products p set p.imagePath = ?1 where p.id = ?2")
    void setImagePathById(String imagePath, UUID productId);

}
