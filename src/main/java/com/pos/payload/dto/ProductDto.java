package com.pos.payload.dto;

import com.pos.modal.Store;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {

    private Long id;


    private String name;


    private String sku;

    private String description;

    private Double mrp;

    private Double sellingPrice;

    private String brand;

    private String image;

   private Long categoryId;

    private Long StoreId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
