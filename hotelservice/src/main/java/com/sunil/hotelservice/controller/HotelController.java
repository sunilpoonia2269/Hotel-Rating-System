package com.sunil.hotelservice.controller;

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

import com.sunil.hotelservice.config.AppConstants;
import com.sunil.hotelservice.payloads.HotelDto;
import com.sunil.hotelservice.payloads.HotelListResponse;
import com.sunil.hotelservice.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<HotelDto> createHotel(@RequestBody @Valid HotelDto hotelDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.createHotel(hotelDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getHotelById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<HotelListResponse> getAllHotels(
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sortDir

    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.fromString(sortDir), sortBy));

        /// Calling method in service layer and sending request
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getAllHotels(pageable));
    }

}
