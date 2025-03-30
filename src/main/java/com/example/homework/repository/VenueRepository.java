package com.example.homework.repository;

import com.example.homework.model.dto.request.VenueRequest;
import com.example.homework.model.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    @Select(
            """
                SELECT * FROM venues
                OFFSET #{offset} LIMIT #{limit};
            """
    )
    List<Venue> getAllVenues(@Param("offset") Integer offset, @Param("limit") Integer size);

    @ResultMap("venueMapper")
    @Select(
            """
                INSERT INTO venues VALUES(default, #{req.venueName}, #{req.location})
                RETURNING *;
            """
    )
    Venue createVenue(@Param("req") VenueRequest request);


    @ResultMap("venueMapper")
    @Select(
            """
                SELECT * FROM venues WHERE venue_id = #{venue_id};
            """
    )
    Venue getVenueById(@Param("venue_id") Long venueId);

    @ResultMap("venueMapper")
    @Select(
           """
              UPDATE venues SET venue_name = #{req.venueName}, location = #{req.location}
              WHERE venue_id = #{venue_id}
              RETURNING *;
           """
    )
    Venue updateVenueById(@Param("venue_id") Long venueId,@Param("req") VenueRequest request);

    @ResultMap("venueMapper")
    @Select(
            """
               DELETE FROM venues WHERE venue_id = #{venue_id}
               RETURNING *;
            """
    )
    Venue deleteVenueById(@Param("venue_id") Long venueId);
}
