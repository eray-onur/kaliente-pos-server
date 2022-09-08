package com.kaliente.pos.domain.useraggregate;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="privileges")
@SQLDelete(sql = "update privileges set is_deleted = true where id =?")
@Where(clause = "is_deleted = false")
public class Privilege extends BaseEntity {

	@Column(unique=true)
	private String title;

	@ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
	
}
