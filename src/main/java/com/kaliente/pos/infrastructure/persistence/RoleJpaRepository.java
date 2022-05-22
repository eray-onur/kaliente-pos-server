package com.kaliente.pos.infrastructure.persistence;

import javax.persistence.EntityManager;

import com.kaliente.pos.domain.useraggregate.Role;
import com.kaliente.pos.domain.useraggregate.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleJpaRepository implements RoleRepository {
    private EntityManager em;

    @Autowired
    RoleJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Role findByTitle(String title) {
        var result = em.createQuery("FROM roles r where r.isActive = true and r.title=:title",
                Role.class).setParameter("title", title).getSingleResult();

        return result;
    }
}
