package com.example.homework.controller;

import com.example.homework.model.dto.request.EventRequest;
import com.example.homework.model.dto.response.ApiResponse;
import com.example.homework.model.entity.Event;
import com.example.homework.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/events")
public class EventController {
    public final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>>  getAllEvents (@RequestParam(defaultValue = "1") Integer page,
                                                                   @RequestParam(defaultValue = "10") Integer size){
        List<Event> events = eventService.getAllEvents(page, size);
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .success(true)
                .message("Get all events successful!")
                .status(HttpStatus.OK)
                .payload(events)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>>  createEvent(@Valid @RequestBody EventRequest request){
        Event event = eventService.createEvent(request);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .success(true)
                .message("Create an event successful!")
                .status(HttpStatus.OK)
                .payload(event)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{event-id}")
    public ResponseEntity<ApiResponse<Event>>  getEventById (@PathVariable("event-id") Long eventId){
        Event event = eventService.getEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .success(true)
                .message("Get an event by" + eventId + " successful!")
                .status(HttpStatus.OK)
                .payload(event)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") Long eventId){
        Event event = eventService.deleteEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .success(true)
                .message("Delete an event by" + eventId + " successful!")
                .status(HttpStatus.OK)
                .payload(event)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{event-id}")
    public ResponseEntity<ApiResponse<Event>>  updateEventById (@PathVariable("event-id") Long eventId,@Valid @RequestBody EventRequest request){
        Event event = eventService.updateEventById(eventId, request);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .success(true)
                .message("Update an event by" + eventId + " successful!")
                .status(HttpStatus.OK)
                .payload(event)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
