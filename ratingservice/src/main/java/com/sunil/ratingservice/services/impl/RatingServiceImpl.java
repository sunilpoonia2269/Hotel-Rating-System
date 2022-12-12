package com.sunil.ratingservice.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sunil.ratingservice.entities.Rating;
import com.sunil.ratingservice.exceptions.ResourceNotFoundException;
import com.sunil.ratingservice.payloads.RatingDto;
import com.sunil.ratingservice.payloads.RatingListResponse;
import com.sunil.ratingservice.repositories.RatingRepo;
import com.sunil.ratingservice.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public RatingDto createRating(RatingDto ratingDto) {
        Rating rating = this.modelMapper.map(ratingDto, Rating.class);

        Rating savedRating = this.ratingRepo.save(rating);

        return this.modelMapper.map(savedRating, RatingDto.class);
    }

    @Override
    public RatingDto getRatingById(String ratingId) {
        Rating rating = this.ratingRepo.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating", "Id", ratingId));
        return this.modelMapper.map(rating, RatingDto.class);
    }

    @Override
    public RatingListResponse getAllRatings(Pageable pageable) {
        Page<Rating> ratingPage = this.ratingRepo.findAll(pageable);
        return this.createRatingListResponse(ratingPage);
    }

    @Override
    public RatingListResponse getRatingByUserId(String userId, Pageable pageable) {
        Page<Rating> ratingPage = this.ratingRepo.findByUserId(userId, pageable);
        return this.createRatingListResponse(ratingPage);
    }

    @Override
    public RatingListResponse getRatingByHotelId(String hotelId, Pageable pageable) {
        Page<Rating> ratingPage = this.ratingRepo.findByHotelId(hotelId, pageable);
        return this.createRatingListResponse(ratingPage);
    }

    ///
    /// Private helper method to convert the page into RatingListResponse
    private RatingListResponse createRatingListResponse(Page<Rating> page) {
        List<Rating> ratings = page.getContent();
        List<RatingDto> ratingDtos = ratings.stream().map((rating) -> this.modelMapper.map(rating, RatingDto.class))
                .collect(Collectors.toList());

        RatingListResponse ratingListResponse = RatingListResponse.builder().content(ratingDtos)
                .totalElements(page.getTotalElements()).currentPage(page.getNumber())
                .totalPages(page.getTotalPages()).lastPage(page.isLast()).pageSize(page.getSize())
                .build();

        return ratingListResponse;
    }

}
