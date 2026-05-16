package com.pos.mapper;

import com.pos.modal.Product;
import com.pos.modal.Store;
import com.pos.payload.dto.ProductDto;

public class ProductMapper {

    public static ProductDto toDTO(Product product){


        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getName())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .StoreId(product.getStore()!=null?product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

    }

    public static Product toEntity(ProductDto productDto, Store store){


        return Product.builder()
                .name(productDto.getName())
                .sku(productDto.getSku())
                .description(productDto.getDescription())
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .brand(productDto.getBrand())
                .build();

    }

}
