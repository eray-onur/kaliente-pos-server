package com.kaliente.pos.application.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.kaliente.pos.application.models.dtos.administration.UpdatePersonnelRequest;
import com.kaliente.pos.domain.useraggregate.Role;
import com.kaliente.pos.domain.useraggregate.User;
import com.kaliente.pos.infrastructure.persistence.RoleHibernateRepository;
import com.kaliente.pos.infrastructure.persistence.UserHibernateRepository;
import com.kaliente.pos.infrastructure.persistence.UserJpaRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdministrationService {
    @Autowired
    UserHibernateRepository userRepository;
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    RoleHibernateRepository roleJpaRepository;

    @Autowired
    ModelMapper mapper;

    public ArrayList<Role> getSystemRoles() throws Exception {
        return this.roleJpaRepository.findAllSystemRoles();
    }

    public User getPersonnelById(UUID id) throws Exception {
        User user = this.userRepository.findById(id);
        return user;
    }

    @Transactional
    public User updatePersonnel(UpdatePersonnelRequest request) throws Exception {
        // LEGACY JPA UPDATE
        // Optional<User> userToUpdate = userJpaRepository.findById(request.getPersonnelId());
        // if(userToUpdate.isEmpty()) return null;
        // userToUpdate.get().setId(request.getPersonnelId());
        // userToUpdate.get().setFirstName(request.getFirstName());
        // userToUpdate.get().setLastName(request.getLastName());
        // userToUpdate.get().getRoles().forEach(r -> r.getUsers().add(userToUpdate.get()));
        // var createdUser = userJpaRepository.save(userToUpdate.get());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        var userToCreate = this.mapper.map(request, User.class);

        var createdUser = userRepository.updateUser(userToCreate);
        return createdUser;
    }

    @Transactional
    public User removePersonnel(String email) throws Exception {
        User user = this.userRepository.findByEmail(email);
        Role rolePersonnel = this.roleJpaRepository.findByTitle("ROLE_PERSONNEL");

        if(user == null) {
            throw new Exception("Personnel does not exist!");
        }

        if (user.getRole().equals(rolePersonnel)) {
            throw new Exception("User is not a personnel, cannot be deleted!");
        }

        boolean result = this.userRepository.archiveUserByEmail(email);

        if(!result) {
            return null;
        }

        return user;
    }
}