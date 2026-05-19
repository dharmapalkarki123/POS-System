package com.pos.service;

import java.util.List;

import com.pos.modal.Users;
import com.pos.payload.dto.BranchDto;

public interface BranchService {
	
	
	BranchDto createBranch(BranchDto branchDto, Users user);
	BranchDto updateBranch(Long id, BranchDto branchDto,Users user);
	void deleteBranch(Long id);
	List<BranchDto> getAllBranchByStoreId(Long StoreId);
	BranchDto getBranchById(Long id);
	
	

}
