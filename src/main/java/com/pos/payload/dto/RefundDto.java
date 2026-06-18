package com.pos.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pos.modal.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RefundDto {

    private Long id;


    private OrderDto order;
    private Long orderId;

    private String reason;

    private Double amount;


//    private ShiftReport shiftReport;
    private Long ShiftReportId;



    private userDto cashier;

    private String cashierName;


    private BranchDto branch;

    private Long branchId;

    private PaymentType paymentType;

    private LocalDateTime createdAt;

}
