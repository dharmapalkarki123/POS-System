package com.pos.mapper;

import com.pos.modal.Category;
import com.pos.payload.dto.CategoryDto;

public class CategoryMapper {

    public static CategoryDto toDTO(Category category) {

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null?category.getStore().getId():null)
                .build();

    }

    public static Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }


}
