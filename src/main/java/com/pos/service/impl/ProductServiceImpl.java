package com.pos.service.impl;

import com.pos.modal.Users;
import com.pos.payload.dto.ProductDto;
import com.pos.repository.ProductRepository;
import com.pos.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public ProductDto createProduct(ProductDto productDto, Users user) {;

    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Users user) {
        return null;
    }

    @Override
    public void deleteProduct(Long id, Users user) {

    }

    @Override
    public List<ProductDto> getProductByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        return List.of();
    }
}
