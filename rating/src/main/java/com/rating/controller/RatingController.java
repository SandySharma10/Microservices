package com.rating.controller;

import com.rating.entity.Rating;
import com.rating.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
public class RatingController {
    private RatingService ratingService;
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating rating1=ratingService.create(rating);
        return new ResponseEntity<>(rating1, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<Rating>> getRating(){
      List<Rating>rating1=  ratingService.getRating();
        return new ResponseEntity<>(rating1, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('admin')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        List<Rating>rating1=  ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(rating1, HttpStatus.OK);
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        List<Rating>rating1=  ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(rating1, HttpStatus.OK);
    }

}
