package com.kaliente.pos.domain.seedwork;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

// Persisted database entity.
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
	
	public BaseEntity() {
		setActive(true);
	}
	
//	@Temporal(TemporalType.TIMESTAMP)
//	protected Date createdOn;
//	
//	protected UUID createdBy;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	protected Date lastModifiedOn;
//	
//	protected UUID lastModifiedBy;
	
	protected boolean isActive;
	
	
	
	
	

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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
