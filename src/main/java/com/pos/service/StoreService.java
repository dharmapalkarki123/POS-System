package com.pos.service;

import com.pos.modal.Users;
import com.pos.payload.dto.storeDto;

import java.util.List;

public interface StoreService {
    storeDto createStore(storeDto StoreDto, Users users);
    storeDto getStoreById(Long id);
    List<storeDto> getAllStore();
    storeDto getStoreByAdmin();
    storeDto updateStore(Long id, storeDto StoreDto, Users users);
    storeDto deleteStore(Long id);
    storeDto getStoreByEmployee();
}
