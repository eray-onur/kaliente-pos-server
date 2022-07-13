package com.kaliente.pos.application.services;

import com.kaliente.pos.domain.useraggregate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getEssentialUsers() {
        List<Role> roles = roleRepository.findAll();
        ArrayList<User> users = new ArrayList<>(userRepository.findAll());

        return users.stream().filter(u ->
                u.getRole().getTitle().equals("ROLE_SUPERADMIN")
        ).toList();
    }

    @Transactional
    public void createEssentialPrivileges() {
        var privileges = privilegeRepository.findAll();
        if(privileges.isEmpty()) {
            System.out.println("No existing privileges in the system.");
            var rPrivilege = new Privilege("READ_PRIVILEGE", new ArrayList<>());
            var wPrivilege = new Privilege("WRITE_PRIVILEGE", new ArrayList<>());

            ArrayList<Privilege> privilegesToAdd = new ArrayList<>();
            privilegesToAdd.add(rPrivilege);
            privilegesToAdd.add(wPrivilege);

            privilegeRepository.saveAll(privilegesToAdd);

        } else System.out.println("Essential privileges already exist.");

    }

    @Transactional
    public void createEssentialRoles() {
        var privileges = privilegeRepository.findAll();

        Optional<Privilege> readPrivilege = privileges.stream().filter(p -> p.getTitle().equals("READ_PRIVILEGE")).findFirst();
        Optional<Privilege> writePrivilege = privileges.stream().filter(p -> p.getTitle().equals("WRITE_PRIVILEGE")).findFirst();

        List<Privilege> essentialPrivileges = Arrays.asList(readPrivilege.get(), writePrivilege.get());

        var roles = roleRepository.findAll();
        if(roles.isEmpty()) {
            System.out.println("No existing roles in the system.");
            // Check for a superadmin and one admin at minimum.
            List<Role> rolesToCreate = new ArrayList<>();

            var superAdminRole = roles.stream().filter(r -> r.getTitle().equals("ROLE_SUPERADMIN")).findFirst();
            var adminRole = roles.stream().filter(r -> r.getTitle().equals("ROLE_ADMIN")).findAny();

            if(superAdminRole.isEmpty()) {
                var newSuperAdminRole = new Role();
                newSuperAdminRole.setTitle("ROLE_SUPERADMIN");
                newSuperAdminRole.setPrivileges(essentialPrivileges);

                rolesToCreate.add(newSuperAdminRole);
            }
            if(adminRole.isEmpty()) {
                var newAdminRole = new Role();
                newAdminRole.setTitle("ROLE_ADMIN");
                newAdminRole.setPrivileges(essentialPrivileges);

                rolesToCreate.add(newAdminRole);
            }

            // Create superadmin and generic admin role.
            roleRepository.saveAll(rolesToCreate);


        } else System.out.println("Essential users already exist.");
    }

    @Transactional
    public void createEssentialUsers() {
        List<Role> roles = roleRepository.findAll();
        List<User> users = userRepository.findAll();
        // Check for existing superadmin and any system admins.
        var foundSuperadmin = users.stream().filter(u -> u.getRole().getTitle().equals("ROLE_SUPERADMIN")).findFirst();
        var foundAdmin = users.stream().filter(u -> u.getRole().getTitle().equals("ROLE_ADMIN")).findAny();

        ArrayList<User> usersToCreate = new ArrayList<>();
        // If none of them exist, create generic superadmin and system admin.
        if(foundSuperadmin.isEmpty()) {

            var superAdminToCreate = User.builder()
                    .userName("Superadmin")
                    .email("Superadmin@Admin.com")
                    .password(passwordEncoder.encode("Test"))
                    .role(roles.stream().filter(r -> r.getTitle().equals("ROLE_SUPERADMIN")).findFirst().get())
                    .personnel(null)
                    .build();

            usersToCreate.add(superAdminToCreate);

        } else System.out.println("Superadmin already exists.");

        if(foundAdmin.isEmpty()) {

            var adminToCreate = User.builder()
                    .userName("Admin")
                    .email("Admin@Admin.com")
                    .password(passwordEncoder.encode("Test"))
                    .role(roles.stream().filter(r -> r.getTitle().equals("ROLE_ADMIN")).findFirst().get())
                    .personnel(null)
                    .build();

            usersToCreate.add(adminToCreate);

        } else System.out.println("A minimum of one system admin already exists.");
    }

}
