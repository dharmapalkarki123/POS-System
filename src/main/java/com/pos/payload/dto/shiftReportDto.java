package com.pos.payload.dto;

import com.pos.modal.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class shiftReportDto {

    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private Double totalSale;
    private Double totalRefunds;

    private Double netSale;
    private Double totalOrders;


    private userDto cashier;
    private Long cashierId;
    private Long branchId;


    private BranchDto branch;


    private List<PaymentSummary> paymentSummary;


    private List<ProductDto> topSellingProducts;


    private List<OrderDto> recentOrders;


    private List<RefundDto> refunds;

}
