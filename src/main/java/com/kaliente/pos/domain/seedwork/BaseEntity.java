package com.kaliente.pos.domain.seedwork;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Persisted database entity.
@Getter
@Setter
@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
	protected UUID id = UUID.randomUUID();

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdOn = new Date();

	@CreatedBy
	protected UUID createdBy;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedOn;

	@LastModifiedBy
	protected UUID lastModifiedBy;
	
	protected boolean isActive;
	
	public BaseEntity() {
		setActive(true);
	}

}
