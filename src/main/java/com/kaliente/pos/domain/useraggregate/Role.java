package com.kaliente.pos.domain.useraggregate;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.JoinColumn;

import com.kaliente.pos.domain.seedwork.BaseEntity;

@Entity(name="roles")
public class Role extends BaseEntity {
	

	
	@Column(unique=true)
	private String title;

	@ManyToMany(mappedBy="roles")
	private Collection<User> users;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "role_privileges",
		joinColumns = @JoinColumn(name = "role_id", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName="id")
	)
	private Collection<Privilege> privileges = new ArrayList<Privilege>();

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


	public Collection<User> getUsers() {
		return users;
	}


	public void setUsers(Collection<User> users) {
		this.users = users;
	}


	public Collection<Privilege> getPrivileges() {
		return privileges;
	}


	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}

	
}
