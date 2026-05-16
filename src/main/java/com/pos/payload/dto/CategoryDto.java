package com.pos.payload.dto;

import com.pos.modal.Store;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private Long storeId;
}
