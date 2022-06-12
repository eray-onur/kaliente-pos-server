package com.kaliente.pos.domain.useraggregate;

import java.util.UUID;

import javax.persistence.*;

import com.kaliente.pos.domain.seedwork.AggregateRoot;
import com.kaliente.pos.domain.seedwork.BaseEntity;

@Entity(name = "users")
public class User  extends BaseEntity implements AggregateRoot {
	
	
	private String userName;
	@Column(updatable = true)
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String email;
	private String password;
	
	@ManyToOne()
	@JoinColumn(name = "role_id", nullable = true)
    private Role role;

	@OneToOne(mappedBy = "user")
	private Personnel personnel;
	
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
