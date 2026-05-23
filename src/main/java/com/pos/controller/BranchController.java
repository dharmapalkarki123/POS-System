package com.pos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.exception.UserException;
import com.pos.payload.dto.BranchDto;
import com.pos.payload.response.ApiResponse;
import com.pos.service.BranchService;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

	private BranchService branchService;

	public BranchController(BranchService branchService) {
		this.branchService = branchService;

	}

	
	@PostMapping("/create")
	public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDTO) throws UserException {

		BranchDto createdBranch = branchService.createBranch(branchDTO);

		return ResponseEntity.ok(createdBranch);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws Exception{
		
		BranchDto requiredBranch=branchService.getBranchById(id);
		
		return ResponseEntity.ok(requiredBranch);
		
		
		
	}
	
	
	
	@GetMapping("/store/{StoreId}")
	public ResponseEntity<List<BranchDto>> getAllBranchByStoreId(@PathVariable Long StoreId){
		
		
		List<BranchDto> allBranches=branchService.getAllBranchByStoreId(StoreId);
		
		return ResponseEntity.ok(allBranches);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<BranchDto> updateBranch11(@PathVariable Long id,@RequestBody BranchDto branchDto) throws Exception{
		
		BranchDto updatedBranch=branchService.updateBranch(id, branchDto);
		
		return ResponseEntity.ok(updatedBranch);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception{
		
		branchService.deleteBranch(id);
		
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Branhc Deleted Sucessfully");
		
		return ResponseEntity.ok(apiResponse);
	}
	
	
	
	
	
}
