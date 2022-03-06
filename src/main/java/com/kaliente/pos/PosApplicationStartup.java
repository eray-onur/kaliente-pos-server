package com.kaliente.pos;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kaliente.pos.domain.useraggregate.ApplicationUser;
import com.kaliente.pos.domain.useraggregate.ApplicationUserRepository;
import com.kaliente.pos.domain.useraggregate.Privilege;
import com.kaliente.pos.domain.useraggregate.PrivilegeRepository;
import com.kaliente.pos.domain.useraggregate.Role;
import com.kaliente.pos.domain.useraggregate.RoleRepository;

@Component
public class PosApplicationStartup implements
ApplicationListener<ContextRefreshedEvent> {
	
    boolean alreadySetup = false;
	
    @Autowired
    private ApplicationUserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private PrivilegeRepository privilegeRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO PRESTART CONFIG EVENT'INI DUZELT!
//		if (alreadySetup)
//            return;
//		
//		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
//		Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//		
//		List<Privilege> adminPrivileges = Arrays.asList(
//		          readPrivilege, writePrivilege);
//		
//		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//		createRoleIfNotFound("ROLE_PERSONNEL", Arrays.asList(readPrivilege));
//		Role adminRole = roleRepository.findByTitle("ROLE_ADMIN");
//		
//		ApplicationUser user = new ApplicationUser();
//		user.setFirstName("Admin");
//		user.setLastName("Admin");
//		user.setPassword(passwordEncoder.encode("test"));
//		user.setEmail("Admin@Admin.com");
//		user.setRoles(Arrays.asList(adminRole));
//		userRepository.save(user);
//		alreadySetup = true;
	}
	
    Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeRepository.findByTitle(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
 
        Role role = roleRepository.findByTitle(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
	}

}
