package com.sunil.ratingservice.payloads;

import java.time.Instant;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
    @JsonProperty(access = Access.READ_ONLY)
    private String id;

    @NotEmpty(message = "User Id is required")
    private String userId;

    @NotEmpty(message = "Hotel Id is required")
    private String hotelId;

    @NotNull(message = "Please provide a Rating")
    @Min(value = 1, message = "Rating is not valid")
    @Max(value = 5, message = "Rating is not valid")
    private Integer rating;

    @Size(min = 2, max = 1000, message = "Feedback should be between 2 to 1000 characters")
    private String feedback;

    @JsonProperty(access = Access.READ_ONLY)
    private Instant createdAt;
}
