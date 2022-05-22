package com.kaliente.pos.infrastructure.persistence;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.kaliente.pos.domain.useraggregate.User;
import com.kaliente.pos.domain.useraggregate.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import antlr.collections.List;

@Repository
public class UserJpaRepository implements UserDetailsService, UserRepository {

    private EntityManager em;

    @Autowired
    UserJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = findByEmail(email);

        if (foundUser == null) {
            throw new UsernameNotFoundException("Invalid email/password.");
        }

        return new org.springframework.security.core.userdetails.User(foundUser.getEmail(), foundUser.getPassword(),
                foundUser.isActive(), true, true, true, getAuthority(foundUser));
    }

    public Set<SimpleGrantedAuthority> getAuthority(User foundUser) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        foundUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getTitle()));
        });
        return authorities;
        // return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public User findByEmail(String email) {
        Query findUserQuery = em.createQuery("SELECT u from users u WHERE u.email=:email",User.class);
        findUserQuery.setParameter("email", email);

        User foundUser = (User) findUserQuery.getSingleResult();
        return foundUser;
    }

    @Override
    public User findByUserName(String username) {
        Query findUserQuery = em.createQuery("SELECT u from users u WHERE u.userName=:username", User.class);
        findUserQuery.setParameter("username", username);

        User foundUser = (User) findUserQuery.getSingleResult();
        return foundUser;
    }

    @Override
    public Collection<User> findAllUsers() {
        
        Collection<User> foundCatalogue = em.createQuery("FROM users",User.class).getResultList();

        return foundCatalogue;

    }

    @Override
    @Transactional
    public User save(User user) {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.merge(user);
    }
    
}
