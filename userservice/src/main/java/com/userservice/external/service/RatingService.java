package com.userservice.external.service;

import com.userservice.entityes.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService
{
    @PostMapping("/ratings")
    Rating createRating(Rating values);
}
