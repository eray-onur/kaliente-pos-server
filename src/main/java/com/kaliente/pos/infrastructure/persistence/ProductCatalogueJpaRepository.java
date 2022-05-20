package com.kaliente.pos.infrastructure.persistence;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.productaggregate.ProductCatalogueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogueJpaRepository {

    private EntityManager em;

    @Autowired
    public ProductCatalogueJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public ProductCatalogue addProductCatalogue(String title, String description, UUID parentCatalogueId) {

        ProductCatalogue createdCatalogue = new ProductCatalogue(title, description);

        if(parentCatalogueId != null) {
            ProductCatalogue parentCatalogue = em.find(ProductCatalogue.class, parentCatalogueId);
            createdCatalogue.setParentCatalogue(parentCatalogue);
        }

        em.merge(createdCatalogue);

        return createdCatalogue;
    }

    @Transactional
    public ProductCatalogue updateProductCatalogue(UUID catalogueId, String title, String description, UUID parentCatalogueId) {
        
        ProductCatalogue foundCatalogue = em.find(ProductCatalogue.class, catalogueId);
        foundCatalogue.setTitle(title);
        foundCatalogue.setDescription(description);

        if (parentCatalogueId != null) {
            ProductCatalogue parentCatalogue = em.find(ProductCatalogue.class, parentCatalogueId);
            foundCatalogue.setParentCatalogue(parentCatalogue);
        }

        em.merge(foundCatalogue);

        return foundCatalogue;
    }
    
}
