package com.pos.controller;


import com.pos.exception.UserException;
import com.pos.mapper.UserMapper;
import com.pos.modal.Users;
import com.pos.payload.dto.userDto;
import com.pos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public ResponseEntity<userDto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        Users user=userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));



    }


    @GetMapping("/{id}")
    public ResponseEntity<userDto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException {
        Users user=userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));



    }

}
