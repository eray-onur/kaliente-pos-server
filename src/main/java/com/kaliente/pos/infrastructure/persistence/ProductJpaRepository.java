package com.kaliente.pos.infrastructure.persistence;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJpaRepository {
    private EntityManager em;


    @Autowired
    public ProductJpaRepository(EntityManager em) {
        this.em = em;
    }

    public Product getProductById(UUID productId) {

        var getByIdQuery = em.createQuery(
                "FROM products p left join fetch p.catalogue pc WHERE p.isActive = true and p.id = :productId",
                Product.class);

        getByIdQuery.setParameter("productId", productId);

        var foundProduct = getByIdQuery.getSingleResult();

        if (foundProduct != null) {
            return foundProduct;
        }

        return null;
    }

    public Collection<Product> getProducts() {

        var result = em.createQuery("FROM products p left join fetch p.catalogue c where p.isActive = true",
                Product.class).getResultList();

        return result;
    }

    @Transactional
    public Product addProduct(String title, String description, double price, UUID catalogueId) {

        Product createdProduct = new Product(title, description, price);

        if (catalogueId != null) {
            ProductCatalogue catalogue = em.find(ProductCatalogue.class, catalogueId);
            if (catalogue != null)
                createdProduct.setCatalogue(catalogue);
        }

        em.merge(createdProduct);

        return createdProduct;
    }

    @Transactional
    public Product updateProduct(UUID productId, String title, String description, double price, UUID catalogueId) {
        // Session session = em.unwrap(Session.class);

        Product foundProduct = em.find(Product.class, productId);
        foundProduct.setTitle(title);
        foundProduct.setDescription(description);
        foundProduct.setPrice(price);

        if (catalogueId == null) {
            foundProduct.setCatalogue(null);
        }
        else {
            ProductCatalogue catalogue = em.find(ProductCatalogue.class, catalogueId);
            if(catalogue != null)
                foundProduct.setCatalogue(catalogue);
        } 

        em.setFlushMode(FlushModeType.COMMIT);
        em.merge(foundProduct);

        return foundProduct;
    }

    @Transactional
    public boolean archiveProduct(UUID productId) {
        Query archiveQuery = em.createQuery("UPDATE products SET isActive = false WHERE id = :productId");
        archiveQuery.setParameter("productId", productId);

        int result = archiveQuery.executeUpdate();

        if (result > 0)
            return true;

        return false;
    }
}
