package com.pos.mapper;

import com.pos.modal.OrderItems;
import com.pos.payload.dto.OrderItemsDto;

public class OrderItemsMapper {

    public static OrderItemsDto toDto(OrderItems orderItems) {

        if(orderItems==null){

            return null;
        }

        return OrderItemsDto.builder()
                .id(orderItems.getId())
                .productId(orderItems.getProduct().getId())
                .quantity(orderItems.getQuantity())
                .price(orderItems.getPrice())
                .product(ProductMapper.toDTO(orderItems.getProduct()))
                .build();

    }

}
