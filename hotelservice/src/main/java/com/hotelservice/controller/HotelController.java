package com.hotelservice.controller;

import com.hotelservice.entity.Hotel;
import com.hotelservice.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("hotels")
public class HotelController {
    private HotelService hotelService;
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1=hotelService.create(hotel);
        return new ResponseEntity<>(hotel1, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('admin')")
    @GetMapping
    ResponseEntity<List<Hotel>>getAllHotel(){
         List<Hotel> hotel1=hotelService.getAll();
         return new ResponseEntity<>(hotel1,HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.OK).body(hotel1);




    }
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    ResponseEntity<Hotel> getHotel(@PathVariable String id){
        Hotel hotel1=hotelService.get(id);
        return new ResponseEntity<>(hotel1,HttpStatus.OK);
    }

}
