package com.pos.controller;

import com.pos.exception.UserException;
import com.pos.modal.Store;
import com.pos.modal.StoreStatus;
import com.pos.modal.Users;
import com.pos.payload.dto.storeDto;
import com.pos.payload.response.ApiResponse;
import com.pos.service.StoreService;
import com.pos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private StoreService storeService;
    private UserService userService;

    public StoreController(StoreService storeService, UserService userService) {
        this.storeService = storeService;
        this.userService = userService;
    }



    @PostMapping
    public ResponseEntity<storeDto> createStore(@RequestBody storeDto StoreDto,
                                                @RequestHeader("Authorization")String jwt) throws UserException {

        Users user=userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(storeService.createStore(StoreDto,user));


    }


    @GetMapping("/{id}")
    public ResponseEntity<storeDto> getStoreById(@PathVariable Long id,
                                                @RequestHeader("Authorization")String jwt) throws Exception {

        Users user=userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(storeService.getStoreById(id));


    }



    @GetMapping
    public ResponseEntity<List<storeDto>> createStore(
                                              @RequestHeader("Authorization")String jwt) throws UserException {



        return ResponseEntity.ok(storeService.getAllStore());


    }




    @PostMapping("/admin")
    public ResponseEntity<Store> getStoreByAdmin(
            @RequestHeader("Authorization")String jwt) throws UserException {



        return ResponseEntity.ok(storeService.getStoreByAdmin());


    }


    @GetMapping("/employee")
    public ResponseEntity<storeDto> getStoreByEmployee(
            @RequestHeader("Authorization")String jwt) throws UserException {



        return ResponseEntity.ok(storeService.getStoreByEmployee());


    }


    @PutMapping("/{id}")
    public ResponseEntity<storeDto> updateStore(@PathVariable Long id,
            @RequestBody storeDto StoreDto) throws Exception {

        Users currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(storeService.updateStore(id, StoreDto,currentUser));


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id
                                                  ) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("User deleted successfully");

        return ResponseEntity.ok(apiResponse);

    }


    @GetMapping("/{id}/moderate")
    public ResponseEntity<storeDto> moderateStore(@PathVariable Long id,
                                                  @RequestParam StoreStatus storeStatus
    ) throws Exception {


        return ResponseEntity.ok(storeService.moderateStore(id, storeStatus));

    }


}
