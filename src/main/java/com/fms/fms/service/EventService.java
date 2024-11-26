package com.fms.fms.service;

import java.util.List;

import com.fms.fms.dto.EventDto;

public interface EventService {
	EventDto createEvent(EventDto evenetDto);
	List<EventDto> getAllEventsByFamilyId(Long familyId);
	List<EventDto> getAllEventsByFamilyMemberId(Long familyMemberId);
	EventDto updateEvent(Long eventId, EventDto eventdto);
	void deleteEvent(Long eventId);
	

}
