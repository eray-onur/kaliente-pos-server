package com.kaliente.pos.domain.useraggregate;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update roles set is_active = false where id =?")
@Where(clause = "is_active = true")
public class Role extends BaseEntity {
	

	
	@Column(unique=true)
	private String title;

	@OneToMany(mappedBy = "role")
	private Collection<User> users;
	
	
	@ManyToMany()
	@JoinTable(
		name = "role_privileges",
		joinColumns = @JoinColumn(name = "role_id", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName="id")
	)
	private Collection<Privilege> privileges = new ArrayList<Privilege>();

	
}
