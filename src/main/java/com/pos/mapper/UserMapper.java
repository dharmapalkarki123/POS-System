package com.pos.mapper;

import com.pos.modal.Users;
import com.pos.payload.dto.userDto;

public class UserMapper {


    public static userDto toDTO(Users savedUser) {

        userDto UserDto=new userDto();
        UserDto.setId(savedUser.getId());
        UserDto.setEmail(savedUser.getEmail());
        UserDto.setRole(savedUser.getRole());
        UserDto.setCreatedAt(savedUser.getCreatedAt());
        UserDto.setUpdatedAt(savedUser.getUpdatedAt());
        UserDto.setLastLogin(savedUser.getLastLogin());
        UserDto.setPhone(savedUser.getPhone());

        return UserDto;


    }
}
