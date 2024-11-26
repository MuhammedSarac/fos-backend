package com.fms.fms.dto;

import java.time.LocalDateTime;

public class TodoDto {
	
	
	public TodoDto() {
		super();
	
	}

	public TodoDto(Long id, String description, String status, String hours, LocalDateTime plannedStartDateTime,
			LocalDateTime planedEndDateTime, Long familyMemberId, Long familyId) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.hours = hours;
		this.planedStartDateTime = plannedStartDateTime;
		this.planedEndDateTime = planedEndDateTime;
		this.familyMemberId = familyMemberId;
		this.familyId = familyId;
	}

	private Long id;
	
	private String description;
	
	private String status;
	
	private String hours;
	
	private LocalDateTime planedStartDateTime;
	
	private LocalDateTime planedEndDateTime;
	
	private Long familyMemberId;
	
	private Long familyId;

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
