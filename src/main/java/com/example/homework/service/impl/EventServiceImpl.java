package com.example.homework.service.impl;

import com.example.homework.exception.NotFoundException;
import com.example.homework.exception.BadRequestException;
import com.example.homework.model.dto.request.EventRequest;
import com.example.homework.model.entity.Event;
import com.example.homework.repository.EventAttendeeRepository;
import com.example.homework.repository.EventRepository;
import com.example.homework.repository.VenueRepository;
import com.example.homework.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;
    public final EventAttendeeRepository eventAttendeeRepository;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<Event> events = eventRepository.getAllEvents(offset, size);
        if (events.isEmpty()) {
            throw new NotFoundException("No events found");
        }
        return events;
    }

    @Override
    public Event getEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event not found with id: " + eventId);
        }
        return event;
    }

    @Override
    public Event createEvent(EventRequest request) {
        if (request == null || request.getAttendeeId() == null || request.getAttendeeId().isEmpty()) {
            throw new BadRequestException("Invalid event request. Attendees cannot be null or empty");
        }
        Event event = eventRepository.createEvent(request);
        if (event == null) {
            throw new BadRequestException("Failed to create event");
        }
        for (Long attendeeId : request.getAttendeeId()) {
            if (attendeeId == null) {
                throw new BadRequestException("Attendee ID cannot be null");
            }
            eventAttendeeRepository.saveEventAttendee(event.getEventId(), attendeeId);
        }
        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) {
        Event existingEvent = eventRepository.getEventById(eventId);
        if (existingEvent == null) {
            throw new NotFoundException("Event not found with id: " + eventId);
        }
        if (request == null || request.getAttendeeId() == null || request.getAttendeeId().isEmpty()) {
            throw new BadRequestException("Invalid event request. Attendees cannot be null or empty");
        }
        return eventRepository.updateEventById(eventId, request);
    }

    @Override
    public Event deleteEventById(Long eventId) {
        Event event = eventRepository.deleteEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event not found with id: " + eventId);
        }
        return event;
    }
}
