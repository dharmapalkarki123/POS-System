package com.pos.service;

import com.pos.exception.UserException;
import com.pos.payload.dto.userDto;
import com.pos.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse  signup(userDto UserDto) throws UserException;
    AuthResponse login(userDto UserDto) throws UserException;

}
