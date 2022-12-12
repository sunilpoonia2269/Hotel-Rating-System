package com.sunil.hotelservice.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sunil.hotelservice.entities.Rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDto {

    @JsonProperty(access = Access.READ_ONLY)
    private String id;

    @NotEmpty(message = "Hotel name is required")
    @Size(min = 3, max = 50, message = "Hotel name is not valid")
    private String name;

    @NotEmpty(message = "Hotel location is required")
    @Size(min = 2, message = "Location is not valid")
    private String location;

    @Size(max = 1000, message = "Hotel description is large")
    private String about;

    @JsonProperty(access = Access.READ_ONLY)
    private List<Rating> ratings = new ArrayList<>();

}
