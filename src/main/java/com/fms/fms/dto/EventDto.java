package com.fms.fms.dto;

import java.time.LocalDateTime;

public class EventDto {
	
	public  EventDto() {
		
	}
	
	
	public EventDto(Long id, LocalDateTime start, LocalDateTime end, String event, Long familyId, Long familyMemberId) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.event = event;
		this.familyId = familyId;
		this.familyMemberId = familyMemberId;
	}


	private Long id;
	private LocalDateTime start;
	private LocalDateTime end;
	private String event;
	private Long familyId;
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
	public Long getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Long familyId) {
		this.familyId = familyId;
	}
	public Long getFamilyMemberId() {
		return familyMemberId;
	}
	public void setFamilyMemberId(Long familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	private Long familyMemberId;
	
	@Override
	public String toString() {
	    return "EventDto{" +
	            "eventDescription='" + event + '\'' +
	            ", start=" + start +
	            ", end='" + end + '\'' +
	            ", did='" + id + '\'' +
	            '}';
	}
	
	

}
