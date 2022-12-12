package com.sunil.userservice.services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sunil.userservice.payloads.RatingListResponse;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/rating-service/rating/get/user/{userId}?unpaged=true")
    RatingListResponse getRatingByUserId(@PathVariable String userId);
}
