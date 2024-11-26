package com.fms.fms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
	
	
	
	public Todo() {
	
	}
	



	public Todo(Long id, String description, String status, String hours, LocalDateTime planedStartDateTime,
			LocalDateTime planedEndDateTime, FamilyMember familyMember, Family family) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.hours = hours;
		this.planedStartDateTime = planedStartDateTime;
		this.planedEndDateTime = planedEndDateTime;
		this.familyMember = familyMember;
		this.family = family;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String status;
	
	private String hours;
	
	private LocalDateTime planedStartDateTime;
	
	private LocalDateTime planedEndDateTime;
	
	@ManyToOne
	@JoinColumn(name = "familyMemberId", referencedColumnName = "id" )
	private FamilyMember familyMember;
	
	@ManyToOne
	@JoinColumn(name = "familyId", referencedColumnName = "id")
	private Family family;
	
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getHours() {
		return hours;
	}


	public void setHours(String hours) {
		this.hours = hours;
	}


	public LocalDateTime getPlanedStartDateTime() {
		return planedStartDateTime;
	}


	public void setPlanedStartDateTime(LocalDateTime planedStartDateTime) {
		this.planedStartDateTime = planedStartDateTime;
	}


	public LocalDateTime getPlanedEndDateTime() {
		return planedEndDateTime;
	}


	public void setPlanedEndDateTime(LocalDateTime planedEndDateTime) {
		this.planedEndDateTime = planedEndDateTime;
	}


	public FamilyMember getFamilyMember() {
		return familyMember;
	}


	public void setFamilyMember(FamilyMember familyMember) {
		this.familyMember = familyMember;
	}


	public Family getFamily() {
		return family;
	}


	public void setFamily(Family family) {
		this.family = family;
	}
	
	

}
