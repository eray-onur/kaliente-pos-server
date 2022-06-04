package com.kaliente.pos.infrastructure.persistence;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.domain.productaggregate.ProductCatalogueRepository;


@Repository
public class ProductCatalogueHibernateRepository implements ProductCatalogueRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ProductCatalogueHibernateRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ProductCatalogue getProductCatalogueById(UUID catalogueId) {

        var getByIdQuery = em.createQuery(
                "FROM product_catalogues pc left join fetch pc.parentCatalogue ppc WHERE pc.isActive = true and pc.id = :catalogueId",
                ProductCatalogue.class);

        getByIdQuery.setParameter("catalogueId", catalogueId);

        var foundCatalogue = getByIdQuery.getSingleResult();

        if(foundCatalogue != null) {
            return foundCatalogue;
        }
        
        return null;
    }

    @Override
    public Collection<ProductCatalogue> getProductCatalogues() {

        var result = em.createQuery("FROM product_catalogues pc left join fetch pc.parentCatalogue ppc WHERE pc.isActive = true", ProductCatalogue.class).getResultList();

        return result;
    }

    @Override
    @Transactional
    public ProductCatalogue addProductCatalogue(String title, String description, UUID parentCatalogueId) {

        ProductCatalogue createdCatalogue = new ProductCatalogue(title, description);

        if(parentCatalogueId == null) {
            createdCatalogue.setParentCatalogue(null);
        }
        else {
            ProductCatalogue parentCatalogue = em.find(ProductCatalogue.class, parentCatalogueId);
            if (parentCatalogue != null)
                createdCatalogue.setParentCatalogue(parentCatalogue);
        }

        em.merge(createdCatalogue);

        return createdCatalogue;
    }

    @Override
    @Transactional
    public ProductCatalogue updateProductCatalogue(UUID catalogueId, String title, String description, UUID parentCatalogueId) {

        ProductCatalogue foundCatalogue = em.find(ProductCatalogue.class, catalogueId);

        foundCatalogue.setTitle(title);
        foundCatalogue.setDescription(description);

        if (parentCatalogueId == null) {
            foundCatalogue.setParentCatalogue(null);
        }
        else {
            ProductCatalogue parentCatalogue = em.find(ProductCatalogue.class, parentCatalogueId);
            if (parentCatalogue != null)
                foundCatalogue.setParentCatalogue(parentCatalogue);
        }

        em.setFlushMode(FlushModeType.COMMIT);
        em.merge(foundCatalogue);

        return foundCatalogue;
    }

    @Override
    @Transactional
    public boolean archiveProductCatalogue(UUID catalogueId) {
        Query archiveQuery = em.createQuery("UPDATE product_catalogues SET isActive = false WHERE id = :catalogueId");
        archiveQuery.setParameter("catalogueId", catalogueId);

        int result = archiveQuery.executeUpdate();
        
        if(result > 0) return true;

        return false;
    }
    
}
