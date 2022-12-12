package com.sunil.userservice.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {

    private String id;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String userId;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String hotelId;

    private Integer rating;

    private String feedback;

    private HotelDto hotelDto;

}
