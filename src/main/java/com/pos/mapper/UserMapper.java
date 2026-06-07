package com.pos.mapper;

import com.pos.modal.Branch;
import com.pos.modal.Users;
import com.pos.payload.dto.userDto;

public class UserMapper {


    public static userDto toDTO(Users savedUser) {

        userDto UserDto=new userDto();
        UserDto.setId(savedUser.getId());
        UserDto.setFullName(savedUser.getFullName());
        UserDto.setEmail(savedUser.getEmail());
        UserDto.setRole(savedUser.getRole());
        UserDto.setCreatedAt(savedUser.getCreatedAt());
        UserDto.setUpdatedAt(savedUser.getUpdatedAt());
        UserDto.setLastLogin(savedUser.getLastLogin());
        UserDto.setBranchId(savedUser.getBranch()!=null?savedUser.getBranch().getId():null);
        UserDto.setStoreId(savedUser.getStore()!=null?savedUser.getStore().getId():null);
        UserDto.setPhone(savedUser.getPhone());


        return UserDto;


    }

    public static Users toEntity(userDto UserDto) {

        Users createdUser=new Users();
        createdUser.setId(UserDto.getId());
        createdUser.setEmail(UserDto.getEmail());
        createdUser.setFullName(UserDto.getFullName());
        createdUser.setRole(UserDto.getRole());
        createdUser.setCreatedAt(UserDto.getCreatedAt());
        createdUser.setUpdatedAt(UserDto.getUpdatedAt());
        createdUser.setLastLogin(UserDto.getLastLogin());
        createdUser.setPhone(UserDto.getPhone());
        createdUser.setBranch(Branch.builder()
                        .id(UserDto.getBranchId())
                .build());

        createdUser.setPassword(UserDto.getPassword());



        return createdUser;
    }
    
    
   
    
}
