package com.sunil.hotelservice.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sunil.hotelservice.entities.Hotel;
import com.sunil.hotelservice.exceptions.ResourceNotFoundException;
import com.sunil.hotelservice.payloads.HotelDto;
import com.sunil.hotelservice.payloads.HotelListResponse;
import com.sunil.hotelservice.repositories.HotelRepo;
import com.sunil.hotelservice.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        /// Creating unique id for hotels
        String randomId = UUID.randomUUID().toString();
        hotelDto.setId(randomId);
        Hotel hotel = this.modelMapper.map(hotelDto, Hotel.class);
        Hotel savedHotel = this.hotelRepo.save(hotel);
        return this.modelMapper.map(savedHotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(String hotelId) {
        Hotel hotel = this.hotelRepo.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "Id", hotelId));
        return this.modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelListResponse getAllHotels(Pageable pageable) {
        Page<Hotel> hotelPage = this.hotelRepo.findAll(pageable);
        List<Hotel> hotels = hotelPage.getContent();
        List<HotelDto> hotelDtos = hotels.stream().map((hotel) -> this.modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
        return this.createHotelListResponse(hotelDtos, hotelPage);

    }

    private HotelListResponse createHotelListResponse(List<HotelDto> hotelDtos, Page<Hotel> hotelPage) {
        HotelListResponse hotelListResponse = HotelListResponse.builder().content(hotelDtos)
                .currentPage(hotelPage.getNumber()).totalPages(hotelPage.getTotalPages())
                .totalElements(hotelPage.getTotalElements()).lastPage(hotelPage.isLast()).pageSize(hotelPage.getSize())
                .build();
        return hotelListResponse;
    }
}
