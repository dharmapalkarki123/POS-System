package com.pos.mapper;

import com.pos.modal.Store;
import com.pos.modal.Users;
import com.pos.payload.dto.storeDto;

public class StoreMapper {

    public static storeDto toDTO(Store store){
        storeDto StoreDto=new storeDto();
         StoreDto.setId(store.getId());
         StoreDto.setBrand(store.getBrand());
         StoreDto.setDescription(store.getDescription());
         StoreDto.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
         StoreDto.setStoreType(store.getStoreType());
         StoreDto.setContact(store.getContact());
         StoreDto.setCreatedAt(store.getCreatedAt());
         StoreDto.setUpdatedAt(store.getUpdatedAt());
         StoreDto.setStatus(store.getStatus());
         return StoreDto;




    }



    public static Store toEntity(storeDto StoreDto, Users storeAdmin){
        Store store=new Store();
        store.setId(StoreDto.getId());
        store.setBrand(StoreDto.getBrand());
        store.setDescription(StoreDto.getDescription());
        store.setStoreAdmin(storeAdmin);
        store.setStoreType(StoreDto.getStoreType());
        store.setContact(StoreDto.getContact());
        store.setCreatedAt(StoreDto.getCreatedAt());
        store.setUpdatedAt(StoreDto.getUpdatedAt());


        return store;
    }


}
