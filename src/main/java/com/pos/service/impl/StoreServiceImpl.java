package com.pos.service.impl;

import com.pos.modal.Users;
import com.pos.payload.dto.storeDto;
import com.pos.repository.StoreRepository;
import com.pos.service.StoreService;
import com.pos.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;

    private UserService userService;

    public StoreServiceImpl(StoreRepository storeRepository, UserService userService) {
        this.storeRepository = storeRepository;
        this.userService = userService;
    }


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
