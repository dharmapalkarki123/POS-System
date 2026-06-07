package com.pos.service.impl;

import com.pos.mapper.CategoryMapper;
import com.pos.modal.Category;
import com.pos.modal.Store;
import com.pos.modal.UserRole;
import com.pos.modal.Users;
import com.pos.payload.dto.CategoryDto;
import com.pos.repository.CategoryRepository;
import com.pos.repository.StoreRepository;
import com.pos.service.CategoryService;
import com.pos.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public CategoryServiceImpl(StoreRepository storeRepository,
                               CategoryRepository categoryRepository,
                               UserService userService)
    {
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
        Users user = userService.getCurrentUser();

        Store store = storeRepository.findById(categoryDto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found")
        );

        Category category = Category.builder()
                .store(store)
                .name(categoryDto.getName())
                .build();

        checkAuthority(user,category.getStore());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategories(Long storeId) {

        List<Category> categories = categoryRepository.findByStoreId(storeId);

        return categories
                .stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("Category not found")
        );


        Users user = userService.getCurrentUser();

        checkAuthority(user,category.getStore());

        category.setName(categoryDto.getName());


        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("Category Not found")
        );

        Users user = userService.getCurrentUser();
        checkAuthority(user,category.getStore());

        categoryRepository.delete(category);


    }



    private void checkAuthority(Users user,Store store) throws Exception {

        boolean isAdmin=user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager=user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore=user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore)&& !isManager){

            throw new Exception("you don't have permission ");



        }
    }}



    


