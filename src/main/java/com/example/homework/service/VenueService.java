package com.example.homework.service;

import com.example.homework.model.dto.request.VenueRequest;
import com.example.homework.model.entity.Venue;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Integer page, Integer size);

    Venue getVenueById(Long venueId);

    Venue createVenue(VenueRequest request);

    Venue updateVenueById(Long venueId, VenueRequest request);

    Venue deleteVenueById(Long venueId);

}
