package com.pos.payload.dto;

import com.pos.modal.Order;
import com.pos.modal.Product;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemsDto {
    private Long id;

    private Integer quantity;

    private Double price;


    private ProductDto product;

    private Long productId;

    private OrderDto order;

    private Long orderId;
}
