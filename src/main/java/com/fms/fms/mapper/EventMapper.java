package com.fms.fms.mapper;

import com.fms.fms.dto.EventDto;
import com.fms.fms.entity.Event;

public class EventMapper {

	public static EventDto mapToEventDto(Event event) {
		return new EventDto(
				event.getId(),
				event.getStart(),
				event.getEnd(),
				event.getEvent(),
				event.getFamilyMemberId(),
				event.getFamilyId()
				);
	}
	
	public static Event mapToEvent (EventDto eventDto) {
		return new Event(
				eventDto.getId(),
				eventDto.getStart(),
				eventDto.getEnd(),
				eventDto.getEvent(),
				eventDto.getFamilyMemberId(),
				eventDto.getFamilyId()
				);
	}
	
}
