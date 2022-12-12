package com.sunil.ratingservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.ratingservice.config.AppConstants;
import com.sunil.ratingservice.payloads.RatingDto;
import com.sunil.ratingservice.payloads.RatingListResponse;
import com.sunil.ratingservice.services.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<RatingDto> createRating(@RequestBody @Valid RatingDto ratingDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.createRating(ratingDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RatingDto> getRatingById(@PathVariable String id) {
        return ResponseEntity.ok(this.ratingService.getRatingById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<RatingListResponse> getAllRatings(
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sorDir,
            @RequestParam(defaultValue = AppConstants.DEFAULT_UNPAGED) boolean unpaged) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.fromString(sorDir), sortBy));
        if (unpaged) {
            pageable = Pageable.unpaged();
        }
        return ResponseEntity.ok(this.ratingService.getAllRatings(pageable));
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<RatingListResponse> getRatingsByUserId(@PathVariable String userId,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sorDir,
            @RequestParam(defaultValue = AppConstants.DEFAULT_UNPAGED) boolean unpaged) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.fromString(sorDir), sortBy));
        if (unpaged) {
            pageable = Pageable.unpaged();
        }
        return ResponseEntity.ok(this.ratingService.getRatingByUserId(userId, pageable));
    }

    @GetMapping("/get/hotel/{hotelId}")
    public ResponseEntity<RatingListResponse> getRatingsByHotelId(@PathVariable String hotelId,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sorDir,
            @RequestParam(defaultValue = AppConstants.DEFAULT_UNPAGED) boolean unpaged) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.fromString(sorDir), sortBy));

        if (unpaged) {
            pageable = Pageable.unpaged();
        }
        return ResponseEntity.ok(this.ratingService.getRatingByHotelId(hotelId, pageable));
    }

}
