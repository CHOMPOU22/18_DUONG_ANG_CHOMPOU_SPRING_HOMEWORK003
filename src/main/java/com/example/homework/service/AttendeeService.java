package com.example.homework.service;

import com.example.homework.model.dto.request.AttendeeRequest;
import com.example.homework.model.entity.Attendee;

import java.util.List;

public interface AttendeeService {

    Attendee getAttendeeById(Long attendeeId);

    Attendee saveAttendee(AttendeeRequest request);

    Attendee deleteAttendeeById(Long attendeeId);

    Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request);

    List<Attendee> getAllAttendees(Integer page, Integer size);
}
