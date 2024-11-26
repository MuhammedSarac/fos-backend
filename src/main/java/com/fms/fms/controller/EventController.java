package com.fms.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.fms.dto.EventDto;
import com.fms.fms.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
	private final EventService eventService;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService=eventService;
	}
	
	
	@PostMapping
	public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto){
		System.out.println("Received request body: " + eventDto); 
		EventDto savedEventDto = eventService.createEvent(eventDto);
		return new ResponseEntity<>(savedEventDto, HttpStatus.CREATED); 
	}
	
	@GetMapping("/getallbyfamily/{id}")
	public ResponseEntity<List<EventDto>> getAllByFamilyId (@PathVariable("id") Long familyId){
		List<EventDto> events = eventService.getAllEventsByFamilyId(familyId);
		return ResponseEntity.ok(events);
	}
	
	@GetMapping("/getallbyfamilymember/{id}")
	public ResponseEntity<List<EventDto>> getAllByFamilyMemberId (@PathVariable("id") Long familyMemberId){
		List<EventDto> events = eventService.getAllEventsByFamilyId(familyMemberId);
		return ResponseEntity.ok(events);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<EventDto> updateEvent (@PathVariable("id") Long eventid, @RequestBody EventDto eventdto){
		System.out.println("event body: "+eventdto);
		EventDto updatedEventDto = eventService.updateEvent(eventid, eventdto);
		return ResponseEntity.ok(updatedEventDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<EventDto> deleteEvent (@PathVariable("id") Long eventId){
		eventService.deleteEvent(eventId);
		return ResponseEntity.noContent().build();
	}
	
	
// to do: test the whole API 
}
