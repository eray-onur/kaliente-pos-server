package com.kaliente.pos.domain.useraggregate;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.kaliente.pos.domain.seedwork.BaseEntity;

@Entity(name="privileges")
public class Privilege extends BaseEntity {

	@Column(unique=true)
	private String title;
	
	public Privilege() {}
	
	public Privilege(String title) {
		super();
		this.title = title;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public Collection<Role> getRoles() {
		return roles;
	}



	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}



	@ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
	
}
