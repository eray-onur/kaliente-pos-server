package com.kaliente.pos.domain.useraggregate;

import java.util.UUID;

import javax.persistence.*;

import com.kaliente.pos.domain.seedwork.AggregateRoot;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
@SQLDelete(sql = "update users set is_deleted = true where id =?")
@Where(clause = "is_deleted = false")
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

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private Personnel personnel;
	
	
	

	
}
