package com.sunil.ratingservice.services;

import org.springframework.data.domain.Pageable;

import com.sunil.ratingservice.payloads.RatingDto;
import com.sunil.ratingservice.payloads.RatingListResponse;

public interface RatingService {
    RatingDto createRating(RatingDto ratingDto);

    RatingDto getRatingById(String ratingId);

    RatingListResponse getAllRatings(Pageable pageable);

    RatingListResponse getRatingByUserId(String userId, Pageable pageable);

    RatingListResponse getRatingByHotelId(String hotelId, Pageable pageable);

}
