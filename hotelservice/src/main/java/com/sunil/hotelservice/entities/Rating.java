package com.sunil.hotelservice.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
    private String id;
    private String userId;
    private String hotelId;

    private Integer rating;
    private String feedback;
}
