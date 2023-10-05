package com.hotelservice.service;

import com.hotelservice.entity.Hotel;
import lombok.AllArgsConstructor;

import java.util.List;


public interface HotelService {
    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);



}
