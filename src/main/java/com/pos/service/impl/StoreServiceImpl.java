package com.pos.service.impl;

import com.pos.exception.UserException;
import com.pos.mapper.StoreMapper;
import com.pos.modal.Store;
import com.pos.modal.Users;
import com.pos.modal.storeContact;
import com.pos.payload.dto.storeDto;
import com.pos.repository.StoreRepository;
import com.pos.service.StoreService;
import com.pos.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        Store store= StoreMapper.toEntity(StoreDto,users);

        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public storeDto getStoreById(Long id) throws Exception {

        Store store=storeRepository.findById(id).orElseThrow(()->
                new Exception("Store not found")
        );

        return StoreMapper.toDTO(store);
    }

    @Override
    public List<storeDto> getAllStore() {

        List<Store> dtos=storeRepository.findAll();

        return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        Users admin=userService.getCurrentUser();

        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public storeDto updateStore(Long id, storeDto StoreDto, Users users) throws Exception {

        Users currentUser=userService.getCurrentUser();
        Store existingUser=storeRepository.findByStoreAdminId(currentUser.getId());
        if(existingUser==null){

            throw new Exception("Store not found");


        }


        existingUser.setBrand(StoreDto.getBrand());
        existingUser.setDescription(StoreDto.getDescription());

        if(StoreDto.getStoreType()!=null){

            existingUser.setStoreType(StoreDto.getStoreType());

        }

        if(StoreDto.getContact()!=null){


            storeContact  contact=storeContact.builder()
                    .address(StoreDto.getContact().getAddress())
                    .phone(StoreDto.getContact().getPhone())
                    .email(StoreDto.getContact().getEmail())
                    .build();
            existingUser.setContact(contact);



        }

        Store updatedStore=storeRepository.save(existingUser);
        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {

        Store store=getStoreByAdmin();

        storeRepository.delete(store);
    }

    @Override
    public storeDto getStoreByEmployee() throws UserException {
        Users currentUser=userService.getCurrentUser();
        if(currentUser==null)
        {

            throw new UserException("You Don't have permission to access this store");


        }

        return StoreMapper.toDTO(currentUser.getStore());
    }
}
