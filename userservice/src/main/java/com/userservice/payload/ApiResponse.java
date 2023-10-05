package com.userservice.payload;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String messgage;
    private boolean success;
    private HttpStatus status;
}
