package com.kaliente.pos.domain.seedwork;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

// Persisted database entity.
@Getter
@Setter
@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
	protected UUID id = UUID.randomUUID();

	@CreatedDate
//	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdOn;

	@CreatedBy
	protected String createdBy;

	@LastModifiedDate
//	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedOn;

	@LastModifiedBy
	protected String lastModifiedBy;
	
	protected boolean isDeleted;
	
	public BaseEntity() {
		setDeleted(false);
	}

	@PrePersist
	public void prePersist() {
		this.createdBy = getUsernameOfAuthenticatedUser();
		this.createdOn = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.lastModifiedBy = getUsernameOfAuthenticatedUser();
		this.lastModifiedOn = new Date();
	}

	private String getUsernameOfAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		return ((User) authentication.getPrincipal()).getUsername();
	}

}
