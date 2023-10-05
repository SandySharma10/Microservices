package com.userservice.services.impl;

import com.userservice.entityes.Hotel;
import com.userservice.entityes.Rating;
import com.userservice.entityes.User;
import com.userservice.exception.ResourceNotFoundException;

import com.userservice.external.service.HotelService;
import com.userservice.repo.UserRepositories;
import com.userservice.services.UserService;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


//@RequiredArgsConstructor

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

private UserRepositories userRepositories;

private RestTemplate restTemplate;
private HotelService hotelService;




    @Override
    public User saveUser(User user) {
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepositories.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepositories.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user= userRepositories.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Resouce Not Found Exception on server which is given Id!!"+userId));

       //Fatch rating for above user from Rating service
        //http://localhost:8083/ratings/user/0aef20c6-433e-4b25-a5d0-568c968f9bc1
       // ArrayList<Rating> ratingsForUser=restTemplate.getForObject("http://localhost:8083/ratings/user/"+user.getUserId(), ArrayList.class);
        //Rating[] ratingsForUser=restTemplate.getForObject("http://localhost:8083/ratings/user/"+user.getUserId(), Rating[].class);
        Rating[] ratingsForUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
        //logger.info("{} ",ratingsForUser);
        System.out.println("{} "+ratingsForUser);
        List<Rating>ratings= Arrays.stream(ratingsForUser).toList();
        List<Rating>ratingList=ratings.stream().map(rating -> {
        //http://localhost:8082/hotels/02d5a75e-b55e-40a2-ae80-2c913d79bad0
            //ResponseEntity<Hotel>forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            //ResponseEntity<Hotel>forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            /*seconed Way using fegin insted of resttemplet*/
           Hotel hotel= hotelService.getHotel(rating.getHotelId());

            //api call to hotel to get the hotel
            //Hotel hotel=forEntity.getBody();
           // System.out.println("response code "+forEntity.getStatusCode());
            //set rating to hotel
            rating.setHotel(hotel);
            //return rating
            return rating;
        }).collect(Collectors.toList());




        user.setRating(ratingList);

        return user;
    }
}
