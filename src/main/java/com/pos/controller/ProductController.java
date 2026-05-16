package com.pos.controller;

import com.pos.modal.Users;
import com.pos.payload.dto.ProductDto;
import com.pos.payload.response.ApiResponse;
import com.pos.repository.StoreRepository;
import com.pos.service.ProductService;
import com.pos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private  ProductService productService;

    private UserService userService;

    public ProductController(ProductService productService,
                             UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
                                                    @RequestHeader("Authorization") String jwt) throws Exception {

        Users user=userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(productService.createProduct(productDto, user));

    }


    @GetMapping("/{id}")
    public ResponseEntity<List<ProductDto>> getByStoreId(@PathVariable Long storeId,
                                                         @RequestHeader("Authorization")String jwt) throws Exception {


        return ResponseEntity.ok(productService.getProductByStoreId(storeId));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestHeader("Authorization")String jwt,
                                                    @RequestBody ProductDto productDto) throws Exception {
        Users user=userService.getUserFromJwtToken(jwt);


        return ResponseEntity.ok(productService.updateProduct(id, productDto, user));

    }



    @GetMapping("store/{storeId}")
    public ResponseEntity<List<ProductDto>> searchByKeyword(@PathVariable Long storeId,
                                                         @RequestParam String keyword,
                                                         @RequestHeader("Authorization")String jwt) throws Exception {


        return ResponseEntity.ok(productService.searchByKeyword(storeId, keyword));

    }


    @DeleteMapping("/{id")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {

        Users user=userService.getUserFromJwtToken(jwt);
      productService.deleteProduct(id,user);

      ApiResponse apiResponse=new ApiResponse();
      apiResponse.setMessage("Product deleted");
      return ResponseEntity.ok(apiResponse);

    }



}
