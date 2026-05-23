package com.pos.payload.dto;

import com.pos.modal.Branch;
import com.pos.modal.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class InventoryDto {

    private Long id;


    private BranchDto branch;

    private Long branchId;

    private Long productId;


    private ProductDto product;


    private Integer quantity;

    private LocalDateTime lastUpdate;


}
