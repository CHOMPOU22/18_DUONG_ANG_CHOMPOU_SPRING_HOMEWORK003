package com.example.homework.service;

import com.example.homework.model.dto.request.EventRequest;
import com.example.homework.model.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);

    Event getEventById(Long eventId);

    Event createEvent(EventRequest request);

    Event updateEventById(Long eventId, EventRequest request);

    Event deleteEventById(Long eventId);
}
