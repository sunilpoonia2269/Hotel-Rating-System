package com.sunil.hotelservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunil.hotelservice.entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
