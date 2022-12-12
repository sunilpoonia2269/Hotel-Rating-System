package com.sunil.hotelservice.services;

import org.springframework.data.domain.Pageable;

import com.sunil.hotelservice.payloads.HotelDto;
import com.sunil.hotelservice.payloads.HotelListResponse;

public interface HotelService {

    HotelDto createHotel(HotelDto hotelDto);

    HotelDto getHotelById(String hotelId);

    HotelListResponse getAllHotels(Pageable pageable);

}
