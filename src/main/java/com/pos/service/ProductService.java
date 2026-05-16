package com.pos.service;

import com.pos.modal.Users;
import com.pos.payload.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, Users user) throws Exception;
    ProductDto updateProduct(Long id,ProductDto productDto, Users user) throws Exception;

    void deleteProduct(Long id, Users user) throws Exception;

    List<ProductDto> getProductByStoreId(Long storeId);

    List<ProductDto> searchByKeyword(Long storeId, String keyword);

}
