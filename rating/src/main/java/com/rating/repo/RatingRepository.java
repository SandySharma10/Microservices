package com.rating.repo;

import com.rating.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {
    //create own method



    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
