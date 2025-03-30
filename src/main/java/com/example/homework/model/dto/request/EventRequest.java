package com.example.homework.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {

    @NotBlank(message = "Event name is mandatory")
    @Size(min = 2, max = 100, message = "Event name must be between 2 and 100 characters")
    private String eventName;

    @NotNull(message = "Event date is mandatory")
    private LocalDate eventDate;

    @NotNull(message = "Venue ID is mandatory")
    private Long venueId;

    @NotNull(message = "Attendee IDs are mandatory")
    private List<Long> attendeeId;
}
