package com.example.homework.service.impl;

import com.example.homework.exception.NotFoundException;
import com.example.homework.exception.BadRequestException;
import com.example.homework.model.dto.request.VenueRequest;
import com.example.homework.model.entity.Venue;
import com.example.homework.repository.VenueRepository;
import com.example.homework.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<Venue> venues = venueRepository.getAllVenues(offset, size);
        if (venues.isEmpty()) {
            throw new NotFoundException("No venues found");
        }
        return venues;
    }

    @Override
    public Venue getVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue not found with id: " + venueId);
        }
        return venue;
    }

    @Override
    public Venue createVenue(VenueRequest request) {
        if (request == null || request.getVenueName() == null || request.getVenueName().isEmpty()) {
            throw new BadRequestException("Invalid venue request. Venue name cannot be null or empty");
        }
        Venue venue = venueRepository.createVenue(request);
        if (venue == null) {
            throw new BadRequestException("Failed to create venue");
        }
        return venue;
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest request) {
        Venue existingVenue = venueRepository.getVenueById(venueId);
        if (existingVenue == null) {
            throw new NotFoundException("Venue not found with id: " + venueId);
        }
        if (request == null || request.getVenueName() == null || request.getVenueName().isEmpty()) {
            throw new BadRequestException("Invalid venue request. Venue name cannot be null or empty");
        }
        return venueRepository.updateVenueById(venueId, request);
    }

    @Override
    public Venue deleteVenueById(Long venueId) {
        Venue venue = venueRepository.deleteVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue not found with id: " + venueId);
        }
        return venue;
    }
}
