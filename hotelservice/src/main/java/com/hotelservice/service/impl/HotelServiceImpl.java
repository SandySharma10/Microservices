package com.hotelservice.service.impl;

import com.hotelservice.entity.Hotel;
import com.hotelservice.exception.ResourceNotFoundException;
import com.hotelservice.repo.HotelRepositories;
import com.hotelservice.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private HotelRepositories hotelRepositories;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId=UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepositories.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepositories.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepositories.findById(id).orElseThrow(()->new ResourceNotFoundException("user not find on server!!"));
    }
}
