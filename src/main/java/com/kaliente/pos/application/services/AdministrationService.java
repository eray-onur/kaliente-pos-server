package com.kaliente.pos.application.services;

import java.util.UUID;

import javax.transaction.Transactional;

import com.kaliente.pos.domain.useraggregate.Role;
import com.kaliente.pos.domain.useraggregate.User;
import com.kaliente.pos.infrastructure.persistence.RoleJpaRepository;
import com.kaliente.pos.infrastructure.persistence.UserJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrationService {
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    RoleJpaRepository roleJpaRepository;

    public User getPersonnelById(UUID id) throws Exception {
        User user = this.userJpaRepository.findById(id);

        return user;
    }

    @Transactional
    public UUID removePersonnel(String email) throws Exception {
        User user = this.userJpaRepository.findByEmail(email);
        Role rolePersonnel = this.roleJpaRepository.findByTitle("ROLE_PERSONNEL");

        if(user == null) {
            throw new Exception("Personnel does not exist!");
        }

        if (user.getRoles().contains(rolePersonnel) == false) {
            throw new Exception("User is not a personnel, cannot be deleted!");
        }

        boolean result = this.userJpaRepository.archiveUserByEmail(email);

        if(!result) {
            return null;
        }

        return user.getId();
    }
}