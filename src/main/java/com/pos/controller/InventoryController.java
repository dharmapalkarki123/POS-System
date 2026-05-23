package com.pos.controller;


import com.pos.payload.dto.InventoryDto;
import com.pos.payload.response.ApiResponse;
import com.pos.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

       private InventoryService inventoryService;

       public InventoryController(InventoryService inventoryService) {
           this.inventoryService = inventoryService;
       }


       @PostMapping("/create")
       ResponseEntity<InventoryDto> create(@RequestBody InventoryDto inventoryDto) throws Exception {


           return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
       }


       @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> update(@PathVariable Long id,
                                               @RequestBody InventoryDto inventoryDto) throws Exception {

           return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDto));
       }

       @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {
           inventoryService.deleteInventory(id);
           ApiResponse apiResponse=new ApiResponse();
           apiResponse.setMessage("Successfully deleted");

           return ResponseEntity.ok(apiResponse);
       }


    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDto> getInventoryByProductAndBranch(
            @PathVariable Long productId,
            @PathVariable Long branchId) throws Exception {

        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId,branchId));
    }







       @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranch(@PathVariable Long branchId) throws Exception {

           return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
       }


}
