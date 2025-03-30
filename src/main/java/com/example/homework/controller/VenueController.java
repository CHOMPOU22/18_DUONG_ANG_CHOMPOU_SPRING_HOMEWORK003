package com.example.homework.controller;

import com.example.homework.model.dto.request.VenueRequest;
import com.example.homework.model.dto.response.ApiResponse;
import com.example.homework.model.entity.Venue;
import com.example.homework.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/venues")
public class VenueController {
    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>>  getAllVenues(@RequestParam(defaultValue = "1") Integer page,
                                                                @RequestParam(defaultValue = "10") Integer size){
        List<Venue> venues = venueService.getAllVenues(page, size);
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .success(true)
                .message("Get all venues successful!")
                .status(HttpStatus.OK)
                .payload(venues)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venue>>  createVenue(@Valid @RequestBody VenueRequest request){
        Venue venue = venueService.createVenue(request);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Create a venue successful!")
                .status(HttpStatus.OK)
                .payload(venue)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Long venueId){
        Venue venue = venueService.getVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Get a venue by " + venueId + " successful!")
                .status(HttpStatus.OK)
                .payload(venue)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable("venue-id") Long venueId,@Valid @RequestBody VenueRequest request){
        Venue venue = venueService.updateVenueById(venueId, request);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Update a venue by " + venueId + " successful!")
                .status(HttpStatus.OK)
                .payload(venue)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") Long venueId){
        Venue venue = venueService.deleteVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Delete a venue by " + venueId + " successful!")
                .status(HttpStatus.OK)
                .payload(venue)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
