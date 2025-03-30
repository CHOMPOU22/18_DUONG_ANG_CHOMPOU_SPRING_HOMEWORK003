package com.example.homework.repository;

import com.example.homework.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {
    @Insert(
            """
                INSERT INTO event_attendee
                VALUES (#{event_id}, #{attendee_id})
                RETURNING *;
            """
    )
    void saveEventAttendee(@Param("event_id") Long eventId, @Param("attendee_id") Long attendeeId);


    @Results(id = "eventAttendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
    })
    @Select(
            """
                SELECT * FROM event_attendee ea
                INNER JOIN attendees a
                ON ea.attendee_id =  a.attendee_id
                WHERE event_id = #{event_id}
            """
    )
    List<Attendee> getAllAttendeesByEventId(@Param("event_id") Long eventId);
}
