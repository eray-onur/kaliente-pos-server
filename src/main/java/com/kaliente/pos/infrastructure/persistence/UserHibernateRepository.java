package com.kaliente.pos.infrastructure.persistence;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.kaliente.pos.domain.useraggregate.User;
import com.kaliente.pos.domain.useraggregate.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


@Repository
public class UserHibernateRepository implements UserDetailsService, UserRepository {

    private EntityManager em;
    

    @Autowired
    UserHibernateRepository(EntityManager em) {
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
    }

    public User findById(UUID userId) {
        Query findUserQuery = em.createQuery("SELECT u from users u WHERE u.id=:userId and u.isActive = true", User.class);
        findUserQuery.setParameter("userId", userId);

        User foundUser = (User) findUserQuery.getSingleResult();
        return foundUser;
    }

    public User findByEmail(String email) {
        Query findUserQuery = em.createQuery("SELECT u from users u left join fetch u.roles WHERE u.email=:email and u.isActive = true",User.class);
        findUserQuery.setParameter("email", email);

        User foundUser = (User) findUserQuery.getSingleResult();
        return foundUser;
    }

    @Override
    public User findByUserName(String username) {
        Query findUserQuery = em.createQuery("SELECT u from users u  WHERE u.userName=:username and u.isActive = true", User.class);
        findUserQuery.setParameter("username", username);

        User foundUser = (User) findUserQuery.getSingleResult();
        return foundUser;
    }

    @Override
    public Collection<User> findAllUsers() {
        
        Collection<User> foundCatalogue = em.createQuery("FROM users u WHERE u.isActive = true",User.class).getResultList();

        return foundCatalogue;

    }

    @Override
    @Transactional
    public User save(User user) {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.merge(user);
    }

    public boolean archiveUser(UUID userId) {
        Query archiveQuery = em.createQuery("UPDATE users SET isActive = false WHERE id = :userId");
        archiveQuery.setParameter("userId", userId);

        int result = archiveQuery.executeUpdate();

        if (result > 0)
            return true;

        return false;
    }

    public boolean archiveUserByEmail(String email) {
        Query archiveQuery = em.createQuery("UPDATE users SET isActive = false WHERE email = :email");
        archiveQuery.setParameter("email", email);

        int result = archiveQuery.executeUpdate();

        if (result > 0)
            return true;

        return false;
    }

    @Transactional
    public User updateUser(User user) {
        // Session session = em.unwrap(Session.class);

        User foundUser = em.find(User.class, user.getId());
        
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());

        em.setFlushMode(FlushModeType.COMMIT);
        em.merge(foundUser);

        return foundUser;
    }
    
}
