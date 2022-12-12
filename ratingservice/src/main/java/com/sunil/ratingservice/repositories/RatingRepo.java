package com.sunil.ratingservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sunil.ratingservice.entities.Rating;

public interface RatingRepo extends MongoRepository<Rating, String> {

    Page<Rating> findByUserId(String userId, Pageable pageable);

    Page<Rating> findByHotelId(String hotelId, Pageable pageable);

}
