package com.pos.payload.dto;

import com.pos.modal.StoreStatus;
import com.pos.modal.Users;
import com.pos.modal.storeContact;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

public class storeDto {

    private Long id;


    private String brand;


    private userDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private   StoreStatus status;



    private storeContact contact;

}
