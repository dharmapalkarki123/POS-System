package com.pos.payload.response;

import com.pos.payload.dto.userDto;
import lombok.Data;

@Data
public class AuthResponse {

    private String token;
    private String message;
    private userDto  user;

}
