package com.sunil.userservice.services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sunil.userservice.payloads.HotelDto;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotel-service/hotel/get/{hotelId}")
    HotelDto getHotelById(@PathVariable String hotelId);

}