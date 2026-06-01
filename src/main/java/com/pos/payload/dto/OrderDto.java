package com.pos.payload.dto;

import com.pos.modal.Branch;
import com.pos.modal.Customer;
import com.pos.modal.OrderItems;
import com.pos.modal.Users;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderDto {

    private Long id;

    private Double totalAmount;


    private LocalDateTime createdAt;

    private Long customerId;
    private Long branchId;

    private BranchDto branch;


    private userDto cashier;


    private Customer customer;

    private List<OrderItemsDto> items;


}
