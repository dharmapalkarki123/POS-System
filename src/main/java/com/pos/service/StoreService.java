package com.pos.service;

import com.pos.exception.UserException;
import com.pos.modal.Store;
import com.pos.modal.StoreStatus;
import com.pos.modal.Users;
import com.pos.payload.dto.storeDto;

import java.util.List;

public interface StoreService {
    storeDto createStore(storeDto StoreDto, Users users);
    storeDto getStoreById(Long id) throws Exception;
    List<storeDto> getAllStore();
    Store getStoreByAdmin() throws UserException;
    storeDto updateStore(Long id, storeDto StoreDto, Users users) throws Exception;
    void deleteStore(Long id) throws UserException;
    storeDto getStoreByEmployee() throws UserException;
    storeDto moderateStore(Long id, StoreStatus storeStatus) throws Exception;
}
