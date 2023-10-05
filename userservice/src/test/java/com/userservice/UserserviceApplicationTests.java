package com.userservice;

import com.userservice.entityes.Rating;
import com.userservice.external.service.RatingService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@SpringBootTest
class UserserviceApplicationTests {
private RatingService ratingService;
	@Test
	void contextLoads() {
	}


//	void createRating(){
//		Rating rating=Rating.builder().rating(10).hotelId("").userId("").feedback("checking with fegin").build();
//		Rating saveRating=ratingService.createRating(rating);
//		System.out.println("save rating");
//	}

}
