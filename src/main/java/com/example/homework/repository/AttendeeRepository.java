package com.example.homework.repository;

import com.example.homework.model.dto.request.AttendeeRequest;
import com.example.homework.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select(
            """
                SELECT * FROM attendees
                OFFSET #{offset} LIMIT #{limit};
            """
    )
    List<Attendee> getAllAttendees(@Param("offset") Integer offset, @Param("limit") Integer size);

    @ResultMap("attendeeMapper")
    @Select(
            """
                INSERT INTO attendees VALUES (default, #{req.attendeeName}, #{req.email})
                RETURNING * ;
            """
    )
    Attendee saveAttendee(@Param("req") AttendeeRequest request);


    @ResultMap("attendeeMapper")
    @Select(
            """
                SELECT * FROM attendees WHERE attendee_id = #{attendee_id};
            """
    )
    Attendee getAttendeeById(@Param("attendee_id") Long attendeeId);


    @ResultMap("attendeeMapper")
    @Select(
            """
                UPDATE attendees SET attendee_name = #{req.attendeeName}, email = #{req.email}
                WHERE attendee_id = #{attendee_id}
                RETURNING *;
            """
    )
    Attendee updateAttendeeById(@Param("attendee_id") Long attendeeId,@Param("req") AttendeeRequest request);

    @ResultMap("attendeeMapper")
    @Select(
            """
                DELETE FROM attendees WHERE attendee_id = #{attendee_id}
                RETURNING *;
            """
    )
    Attendee deleteAttendeeById(@Param("attendee_id") Long attendeeId);
}
