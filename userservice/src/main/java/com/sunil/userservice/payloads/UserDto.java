package com.sunil.userservice.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sunil.userservice.config.AppConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @JsonProperty(access = Access.READ_ONLY)
    private String id;

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name must be between 3 to 30 char")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Provide a valid email")
    @Pattern(regexp = AppConstants.EMAIL_VALIDATION_PATTERN, message = "Email is not valid")
    private String email;

    @Size(max = 250, message = "About can be only 250 char")
    private String about;

    private List<RatingDto> ratings = new ArrayList<>();
}
