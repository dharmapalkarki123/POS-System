package com.pos.service;

import com.pos.modal.Users;
import com.pos.payload.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, Users user);
    ProductDto updateProduct(ProductDto productDto, Users user);

    void deleteProduct(Long id, Users user);

    List<ProductDto> getProductByStoreId(Long storeId);

    List<ProductDto> searchByKeyword(Long storeId, String keyword);

}
