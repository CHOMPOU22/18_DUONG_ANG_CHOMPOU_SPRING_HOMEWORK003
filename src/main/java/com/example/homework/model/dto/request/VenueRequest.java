package com.example.homework.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueRequest {

    @NotBlank(message = "Venue name is mandatory")
    @Size(min = 2, max = 100, message = "Venue name must be between 2 and 100 characters")
    private String venueName;

    @NotBlank(message = "Location is mandatory")
    @Size(min = 2, max = 200, message = "Location must be between 2 and 200 characters")
    private String location;
}
