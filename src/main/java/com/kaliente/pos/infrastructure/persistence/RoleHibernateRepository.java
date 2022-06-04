package com.kaliente.pos.infrastructure.persistence;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.kaliente.pos.domain.useraggregate.Role;
import com.kaliente.pos.domain.useraggregate.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleHibernateRepository implements RoleRepository {
    
    private EntityManager em;

    @Autowired
    RoleHibernateRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Role findByTitle(String title) {
        var result = em.createQuery("FROM roles r where r.isActive = true and r.title=:title",
                Role.class).setParameter("title", title).getSingleResult();

        return result;
    }

    @Override
    public ArrayList<Role> findAllSystemRoles() {
        TypedQuery<Role> query = em.createQuery("FROM roles", Role.class);

        var result = new ArrayList<Role>();
        result.addAll(query.getResultList());
        return result;
    }
}
