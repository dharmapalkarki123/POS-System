package com.pos.payload.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.pos.modal.Store;
import com.pos.modal.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BranchDto {
	
private Long id;
	
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String email;
	

	private List<String> workingDays;
	
	private LocalTime openTime;
	private LocalTime closeTime;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private storeDto StoreDto;
	
	private Long StoreId;
	

	private userDto manager;

}
