package com.pos.controller;

import com.pos.exception.UserException;
import com.pos.payload.dto.userDto;
import com.pos.payload.response.AuthResponse;
import com.pos.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//http://localhost:5000/auth/signup

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandeler(
            @RequestBody userDto UserDto
    ) throws UserException {

        return ResponseEntity.ok(authService.signup(UserDto));

    }



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> LoginHandeler(
            @RequestBody userDto UserDto
    ) throws UserException {

        return ResponseEntity.ok(authService.login(UserDto));

    }

}
