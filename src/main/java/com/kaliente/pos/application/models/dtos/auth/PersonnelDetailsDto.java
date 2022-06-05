package com.kaliente.pos.application.models.dtos.auth;

import java.util.UUID;

public class PersonnelDetailsDto {
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String roleTitle;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public String getRoleTitle() {
		return this.roleTitle;
	}
	public void setRoleTitle(String roleTitle) {
		this.roleTitle = roleTitle;
	}
}
