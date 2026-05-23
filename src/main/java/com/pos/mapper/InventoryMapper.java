package com.pos.mapper;

import com.pos.modal.Branch;
import com.pos.modal.Inventory;
import com.pos.modal.Product;
import com.pos.payload.dto.InventoryDto;

import java.time.LocalDateTime;

public class InventoryMapper {

    public static InventoryDto toDto(Inventory inventory) {

        return InventoryDto.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDTO(inventory.getProduct()))
                .branch(BranchMapper.toDTO(inventory.getBranch()))
                .quantity(inventory.getQuantity())
                .lastUpdate(LocalDateTime.now())
                .build();
    }


    public static Inventory toEntity(InventoryDto inventoryDto,
                                     Branch branch,
                                     Product product) {

        return Inventory.builder()
                .id(inventoryDto.getId())
                .branch(branch)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .build();

    }

}
