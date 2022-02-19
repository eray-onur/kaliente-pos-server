package com.kaliente.pos.domain.seedwork;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
	protected UUID id = UUID.randomUUID();
	
//	@Temporal(TemporalType.TIMESTAMP)
//	protected Date createdOn;
//	
//	protected UUID createdBy;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	protected Date lastModifiedOn;
//	
//	protected UUID lastModifiedBy;
	
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
//	public Date getCreatedOn() {
//		return createdOn;
//	}
//	public void setCreatedOn(Date createdOn) {
//		this.createdOn = createdOn;
//	}
//	public UUID getCreatedBy() {
//		return createdBy;
//	}
//	public void setCreatedBy(UUID createdBy) {
//		this.createdBy = createdBy;
//	}
//	public Date getLastModifiedOn() {
//		return lastModifiedOn;
//	}
//	public void setLastModifiedOn(Date lastModifiedOn) {
//		this.lastModifiedOn = lastModifiedOn;
//	}
//	public UUID getLastModifiedBy() {
//		return lastModifiedBy;
//	}
//	public void setLastModifiedBy(UUID lastModifiedBy) {
//		this.lastModifiedBy = lastModifiedBy;
//	}
}
