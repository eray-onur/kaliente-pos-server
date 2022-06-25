package com.kaliente.pos.application.configs;


import com.kaliente.pos.application.filters.JwtRequestFilter;
import com.kaliente.pos.infrastructure.persistence.UserHibernateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages="com.kaliente.pos")
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private UserHibernateRepository userJpaRepository;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userJpaRepository);
	}
	
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.cors().and()
			.authorizeRequests(authorizeRequests -> {
				authorizeRequests
		        .antMatchers("/auth/register", "/auth/authenticate", "/auth/health_check").permitAll()
		        .anyRequest().authenticated();
			})
			.csrf(csrf -> {
				csrf.disable();
			})
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public RoleHierarchy roleHierarchy() {
	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	    String hierarchy = "ROLE_SUPERADMIN > ROLE_ADMIN > ROLE_PERSONNEL";
	    roleHierarchy.setHierarchy(hierarchy);
	    return roleHierarchy;
	}
	
	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
	    expressionHandler.setRoleHierarchy(roleHierarchy());
	    return expressionHandler;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
	    return new BCryptPasswordEncoder();
	}

}
