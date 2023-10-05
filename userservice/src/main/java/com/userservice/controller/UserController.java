package com.userservice.controller;

import com.userservice.entityes.User;
import com.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return new ResponseEntity<User>(user1, HttpStatus.CREATED);
        //return  ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }


    @GetMapping("/{userId}")
    @CircuitBreaker(name="ratingHotelBraker",fallbackMethod = "ratingHotelFallback")
    //@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    //@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User>getSingleUser(@PathVariable String userId){

        Integer retryCount=1;
     User user=userService.getUser(userId);
     System.out.println("retryCount"+retryCount);
        retryCount++;
     return new ResponseEntity<>(user,HttpStatus.OK);
     //return new ResponseEntity.ok(user);
    }
    //creating fallback method for circuitBraker
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
        System.out.println("fallback excute because service is down"+ex.getMessage());
        User user=User.builder().
                email("dummy@gmail.com").
                name("dummy").
                about("this is dummy user its created because servive ic down").userId("4137568")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<User>>getAllUser(){
        List<User>list=userService.getAllUser();
        return new ResponseEntity<>(list,HttpStatus.OK);

    }

}
