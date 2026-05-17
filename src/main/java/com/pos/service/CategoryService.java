package com.pos.service;

import com.pos.exception.UserException;
import com.pos.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
    List<CategoryDto> getCategories(Long id);
    CategoryDto updateCategory(Long id,CategoryDto categoryDto) throws Exception;
    void deleteCategory(Long id) throws Exception;



}
