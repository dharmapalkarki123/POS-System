package com.pos.service.impl;

import com.pos.mapper.ProductMapper; 
import com.pos.modal.Category;
import com.pos.modal.Product;
import com.pos.modal.Store;
import com.pos.modal.Users;
import com.pos.payload.dto.ProductDto;
import com.pos.repository.CategoryRepository;
import com.pos.repository.ProductRepository;
import com.pos.repository.StoreRepository;
import com.pos.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, StoreRepository storeRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ProductDto createProduct(ProductDto productDto, Users user) throws Exception {

        Store store = storeRepository.findById(productDto.getStoreId().longValue()
        ).orElseThrow(
                () -> new Exception("Store not found")
        );

        Category category=categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                () -> new Exception("Category not found")
        );

        Product product = ProductMapper.toEntity(productDto, store,category);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);


    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, Users user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception("Product Not found")
        );





        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setImage(productDto.getImage());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setBrand(productDto.getBrand());
        product.setUpdatedAt(LocalDateTime.now());

        if(productDto.getCategoryId()!=null){
            Category category=categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    () -> new Exception("Category not found")
            );

            product.setCategory(category);
        }



        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);


    }

    @Override
    public void deleteProduct(Long id, Users user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception("Product not found")
        );


        productRepository.delete(product);

    }

    @Override
    public List<ProductDto> getProductByStoreId(Long storeId) {

        List<Product> products = productRepository.findByStoreId(storeId);

        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepository.searchByKeyword(storeId, keyword);

        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
