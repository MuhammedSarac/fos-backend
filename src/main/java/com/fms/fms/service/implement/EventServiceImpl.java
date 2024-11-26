package com.fms.fms.service.implement;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fms.fms.dto.EventDto;
import com.fms.fms.entity.Event;
import com.fms.fms.exception.ResourceNotFoundException;
import com.fms.fms.mapper.EventMapper;
import com.fms.fms.repository.EventRepository;
import com.fms.fms.service.EventService;

import jakarta.transaction.Transactional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public EventDto createEvent(EventDto eventDto) {
        // Convert start and end to UTC ZonedDateTime before saving
        ZonedDateTime startZoned = ZonedDateTime.of(eventDto.getStart(), ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime endZoned = ZonedDateTime.of(eventDto.getEnd(), ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));

        // Map EventDto to Event, set the correct start and end time
        Event event = EventMapper.mapToEvent(eventDto);
        event.setStart(startZoned.toLocalDateTime());
        event.setEnd(endZoned.toLocalDateTime());
        
        Event savedEvent = eventRepository.save(event);
        
        // Map the saved event to DTO and return
        return EventMapper.mapToEventDto(savedEvent);
    }

    @Override
    public List<EventDto> getAllEventsByFamilyId(Long familyId) {
        List<Event> events = eventRepository.findByFamilyId(familyId);
        
        return events.stream()
                .map(event -> {
                    // Convert the stored LocalDateTime back to ZonedDateTime (assumed UTC)
                    ZonedDateTime startZoned = event.getStart().atZone(ZoneId.of("UTC"));
                    ZonedDateTime endZoned = event.getEnd().atZone(ZoneId.of("UTC"));

                    // Convert to system default time zone (user's local time)
                    event.setStart(startZoned.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
                    event.setEnd(endZoned.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
                    
                    return EventMapper.mapToEventDto(event);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getAllEventsByFamilyMemberId(Long familyMemberId) {
        List<Event> events = eventRepository.findByFamilyMemberId(familyMemberId);
        
        return events.stream()
                .map(event -> {
                    // Convert the stored LocalDateTime back to ZonedDateTime (assumed UTC)
                    ZonedDateTime startZoned = event.getStart().atZone(ZoneId.of("UTC"));
                    ZonedDateTime endZoned = event.getEnd().atZone(ZoneId.of("UTC"));

                    // Convert to system default time zone (user's local time)
                    event.setStart(startZoned.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
                    event.setEnd(endZoned.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
                    
                    return EventMapper.mapToEventDto(event);
                })
                .collect(Collectors.toList());
    }

    @Override
    public EventDto updateEvent(Long eventId, EventDto eventDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event is not found with id : " + eventId));

        // Convert start and end to UTC ZonedDateTime before saving
        ZonedDateTime startZoned = ZonedDateTime.of(eventDto.getStart(), ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime endZoned = ZonedDateTime.of(eventDto.getEnd(), ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));

        // Update event with new values
        event.setStart(startZoned.toLocalDateTime());
        event.setEnd(endZoned.toLocalDateTime());
        event.setEvent(eventDto.getEvent());
        event.setFamilyId(eventDto.getFamilyId());
        event.setFamilyMemberId(eventDto.getFamilyMemberId());

        Event updatedEvent = eventRepository.save(event);
        
        // Map the updated event to DTO and return
        return EventMapper.mapToEventDto(updatedEvent);
    }

    @Override
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event is not found with id : " + eventId));
        eventRepository.delete(event);
    }
}
