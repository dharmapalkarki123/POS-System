package com.pos.service.impl;

import com.pos.modal.Users;
import com.pos.payload.dto.storeDto;
import com.pos.service.StoreService;

import java.util.List;

public class StoreServiceImpl implements StoreService {
    @Override
    public storeDto createStore(storeDto StoreDto, Users users) {
        return null;
    }

    @Override
    public storeDto getStoreById(Long id) {
        return null;
    }

    @Override
    public List<storeDto> getAllStore() {
        return List.of();
    }

    @Override
    public storeDto getStoreByAdmin() {
        return null;
    }

    @Override
    public storeDto updateStore(Long id, storeDto StoreDto, Users users) {
        return null;
    }

    @Override
    public storeDto deleteStore(Long id) {
        return null;
    }

    @Override
    public storeDto getStoreByEmployee() {
        return null;
    }
}
