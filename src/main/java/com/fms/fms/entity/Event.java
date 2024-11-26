package com.fms.fms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="events")
@Entity
public class Event {

	public  Event() {
		
	}
	
	
	public Event(Long id, LocalDateTime start, LocalDateTime end, String event, Long familyMemberId, Long familyId) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.event = event;
		this.familyMemberId = familyMemberId;
		this.familyId = familyId;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id ;
	
	@Column(nullable = false)
	private LocalDateTime start;
	
	@Column(nullable = false)
	private LocalDateTime end;
	
	@Column(nullable = false)
	private String event;
	
	@Column(nullable = false)
	private Long familyMemberId;
	
	@Column(nullable = false)
	private Long familyId;
	
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Long getFamilyMemberId() {
		return familyMemberId;
	}

	public void setFamilyMemberId(Long familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	public Long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Long familyId) {
		this.familyId = familyId;
	}
	
	
}
