package com.example.homework.controller;

import com.example.homework.model.dto.request.AttendeeRequest;
import com.example.homework.model.dto.response.ApiResponse;
import com.example.homework.model.entity.Attendee;
import com.example.homework.service.AttendeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(defaultValue = "10") Integer size){
        List<Attendee> attendees = attendeeService.getAllAttendees(page, size);
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .success(true)
                .message("Get all attendees successful!")
                .status(HttpStatus.OK)
                .payload(attendees)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>>  saveAttendee(@Valid @RequestBody AttendeeRequest request){
        Attendee attendee = attendeeService.saveAttendee(request);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .success(true)
                .message("Save attendee successful!")
                .status(HttpStatus.OK)
                .payload(attendee)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Long attendeeId){
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .success(true)
                .message("Get attendee " + attendeeId + " successful!")
                .status(HttpStatus.OK)
                .payload(attendee)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>>  updateAttendeeById(@PathVariable("attendee-id") Long attendeeId,@Valid @RequestBody AttendeeRequest request){
        Attendee attendee = attendeeService.updateAttendeeById(attendeeId, request);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .success(true)
                .message("Update attendee " + attendeeId + " successful!")
                .status(HttpStatus.OK)
                .payload(attendee)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(@PathVariable("attendee-id") Long attendeeId){
        Attendee attendee = attendeeService.deleteAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .success(true)
                .message("Delete attendee " + attendeeId + " successful!")
                .status(HttpStatus.OK)
                .payload(attendee)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
