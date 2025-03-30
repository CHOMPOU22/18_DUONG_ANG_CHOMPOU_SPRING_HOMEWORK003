package com.example.homework.repository;

import com.example.homework.model.dto.request.EventRequest;
import com.example.homework.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "com.example.homework.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id", many = @Many(select = "com.example.homework.repository.EventAttendeeRepository.getAllAttendeesByEventId"))
    })
    @Select(
            """
                SELECT * FROM events
                OFFSET #{offset} LIMIT #{limit};
            """
    )
    List<Event> getAllEvents(@Param("offset") Integer offset, @Param("limit") Integer size);


    @ResultMap("eventMapper")
    @Select(
            """
                SELECT * FROM events WHERE event_id = #{event_id}
            """
    )
    Event getEventById(@Param("event_id") Long eventId);

    @ResultMap("eventMapper")
    @Select(
            """
                INSERT INTO events VALUES (default, #{req.eventName}, #{req.eventDate}, #{req.venueId})
                RETURNING *;
            """
    )
    Event createEvent(@Param("req") EventRequest request);


    @ResultMap("eventMapper")
    @Select(
            """
                UPDATE events SET event_name = #{req.eventName}, event_date = #{req.eventDate}, venue_id = #{req.venueId}
                WHERE event_id = #{event_id}
                RETURNING *;
            """
    )
    Event updateEventById(@Param("event_id") Long eventId,@Param("req") EventRequest request);


    @ResultMap("eventMapper")
    @Select(
            """
                DELETE FROM events WHERE event_id = #{event_id}
                RETURNING *;
            """
    )
    Event deleteEventById(@Param("event_id") Long eventId);

}
