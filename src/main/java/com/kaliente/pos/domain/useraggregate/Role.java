package com.kaliente.pos.domain.useraggregate;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.JoinColumn;

import com.kaliente.pos.domain.seedwork.BaseEntity;

@Entity(name="role")
public class Role extends BaseEntity {
	
	private String title;
	
	public Role() {}
	
	public Role(String title) {
		super();
		this.title = title;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Collection<ApplicationUser> getUsers() {
		return users;
	}


	public void setUsers(Collection<ApplicationUser> users) {
		this.users = users;
	}


	public Collection<Privilege> getPrivileges() {
		return privileges;
	}


	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}


	@ManyToMany(mappedBy="roles")
	private Collection<ApplicationUser> users;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "role_privileges",
		joinColumns = @JoinColumn(name = "role_id", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName="id")
	)
	private Collection<Privilege> privileges;
	
}
