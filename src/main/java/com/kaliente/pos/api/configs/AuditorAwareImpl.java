package com.kaliente.pos.api.configs;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.kaliente.pos.domain.useraggregate.UserRepository;

@Component
public class AuditorAwareImpl implements AuditorAware<UUID> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<UUID> getCurrentAuditor() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
		
        org.springframework.security.core.userdetails.User authenticatedUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        
        UUID auditorId = userRepository.findByEmail(authenticatedUser.getUsername()).getId();
		
		// TODO Auto-generated method stub
		return Optional.of(auditorId);
	}
	
}