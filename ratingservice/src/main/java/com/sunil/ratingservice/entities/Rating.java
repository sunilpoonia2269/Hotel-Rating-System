package com.sunil.ratingservice.entities;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("ratings")
@CompoundIndex(def = "{'userId':1, 'hotelId':1}", unique = true)
public class Rating {
    @Id
    private String id;

    private String userId;
    private String hotelId;
    private Integer rating;
    private String feedback;

    @CreatedDate
    private Instant createdAt;

}
