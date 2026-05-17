package com.pos.controller;


import com.pos.payload.dto.CategoryDto;
import com.pos.payload.response.ApiResponse;
import com.pos.repository.CategoryRepository;
import com.pos.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")


public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws Exception {


        return ResponseEntity.ok(categoryService.createCategory(categoryDto));

    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByStoreId(@PathVariable Long storeId) throws Exception {


        return ResponseEntity.ok(categoryService.getCategories(storeId));

    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                      @RequestBody CategoryDto categoryDto) throws Exception {


        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));


    }

    @DeleteMapping("/{id}")

    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) throws Exception {


        categoryService.deleteCategory(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Category deleted successfully");

        return ResponseEntity.ok(apiResponse);


    }

}