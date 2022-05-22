package com.kaliente.pos.infrastructure.persistence;

import javax.persistence.EntityManager;

import com.kaliente.pos.domain.useraggregate.Privilege;
import com.kaliente.pos.domain.useraggregate.PrivilegeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PrivilegeJpaRepository implements PrivilegeRepository {
    private EntityManager em;

    @Autowired
    PrivilegeJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Privilege findByTitle(String title) {
        var result = em.createQuery("FROM privileges p where p.isActive = true and p.title=:title",
                Privilege.class).setParameter("title", title).getSingleResult();
        return result;
    }
}
