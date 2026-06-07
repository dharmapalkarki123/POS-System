package com.pos.payload.dto;

import com.pos.modal.UserRole;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class userDto {


    private Long id;

    private String fullName;


    private Long storeId;



    private Long branchId;

    private String email;

    private String phone;

    private String password;

    private UserRole role;



    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;


}
