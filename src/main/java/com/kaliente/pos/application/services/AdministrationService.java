package com.kaliente.pos.application.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.kaliente.pos.application.requests.administration.UpdatePersonnelRequest;
import com.kaliente.pos.domain.useraggregate.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdministrationService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PrivilegeRepository privilegeRepository;
    ModelMapper modelMapper;

    @Autowired
    public AdministrationService(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.modelMapper = modelMapper;
    }

    public ArrayList<Privilege> getSystemPrivileges() {
        return new ArrayList<>(this.privilegeRepository.findAll());
    }
    public ArrayList<Role> getSystemRoles() {
        return new ArrayList<>(this.roleRepository.findAll());
    }

    public User getPersonnelById(UUID id) throws Exception {
        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty())
            return null;

        return user.get();
    }

    @Transactional
    public User updatePersonnel(UpdatePersonnelRequest request) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        var userToCreate = this.modelMapper.map(request, User.class);

        return userRepository.save(userToCreate);
    }

    @Transactional
    public User removePersonnel(String email) throws Exception {
        User user = this.userRepository.findByEmail(email);
        Role rolePersonnel = this.roleRepository.findByTitle("ROLE_PERSONNEL");

        if(user == null) {
            throw new Exception("Personnel does not exist!");
        }

        if (!user.getRole().equals(rolePersonnel)) {
            throw new Exception("User is not a personnel, cannot be deleted!");
        }

        this.userRepository.delete(user);

        return user;
    }
}