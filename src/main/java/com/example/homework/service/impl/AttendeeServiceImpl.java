package com.example.homework.service.impl;

import com.example.homework.exception.BadRequestException;
import com.example.homework.exception.NotFoundException;
import com.example.homework.model.dto.request.AttendeeRequest;
import com.example.homework.model.entity.Attendee;
import com.example.homework.repository.AttendeeRepository;
import com.example.homework.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public Attendee getAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if (attendee == null) {
            throw new NotFoundException("Attendee not found with id: " + attendeeId);
        }
        return attendee;
    }

    @Override
    public Attendee saveAttendee(AttendeeRequest request) {
        if (request == null) {
            throw new BadRequestException("Attendee request cannot be null");
        }
        return attendeeRepository.saveAttendee(request);
    }

    @Override
    public Attendee deleteAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.deleteAttendeeById(attendeeId);
        if (attendee == null) {
            throw new NotFoundException("Attendee not found with id: " + attendeeId);
        }
        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request) {
        if (request == null) {
            throw new BadRequestException("Attendee request cannot be null");
        }
        Attendee attendee = attendeeRepository.updateAttendeeById(attendeeId, request);
        if (attendee == null) {
            throw new NotFoundException("Attendee not found with id: " + attendeeId);
        }
        return attendee;
    }

    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<Attendee> attendees = attendeeRepository.getAllAttendees(offset, size);
        if (attendees.isEmpty()) {
            throw new NotFoundException("No attendees found");
        }
        return attendees;
    }
}
