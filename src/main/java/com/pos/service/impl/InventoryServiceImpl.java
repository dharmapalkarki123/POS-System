package com.pos.service.impl;

import com.pos.mapper.InventoryMapper;
import com.pos.modal.Branch;
import com.pos.modal.Inventory;
import com.pos.modal.Product;
import com.pos.payload.dto.InventoryDto;
import com.pos.repository.BranchRepository;
import com.pos.repository.InventoryRepository;
import com.pos.repository.ProductRepository;
import com.pos.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private  ProductRepository productRepository;
    private  BranchRepository branchRepository;
    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, BranchRepository branchRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }


    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {

        Branch branch=branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(
                ()-> new Exception("branch not found")
        );

        Product product=productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                ()-> new Exception("Product not Exist")
        );

        Inventory inventory= InventoryMapper.toEntity(inventoryDto,branch,product);
        Inventory savedInventory=inventoryRepository.save(inventory);

        return InventoryMapper.toDto(savedInventory);
    }

    @Override
    public InventoryDto updateInventory(Long id,InventoryDto inventoryDto) throws Exception {

        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory not found")
        );

        inventory.setQuantity(inventoryDto.getQuantity());
        Inventory updatedInventory=inventoryRepository.save(inventory);



        return InventoryMapper.toDto(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {

        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory not found")
        );

        inventoryRepository.delete(inventory);

    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory not found")
        );
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {

        Inventory inventory=inventoryRepository.findByProductIdAndBranchId(productId, branchId);

        return InventoryMapper.toDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoryByBranchId(Long branchId) {

        List<Inventory> inventoryList=inventoryRepository.findByBranchId(branchId);


        return inventoryList.stream().map(InventoryMapper::toDto).collect(Collectors.toList());
    }
}
