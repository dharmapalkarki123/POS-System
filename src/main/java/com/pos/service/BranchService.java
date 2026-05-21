package com.pos.service;

import java.util.List;

import com.pos.exception.UserException;
import com.pos.modal.Users;
import com.pos.payload.dto.BranchDto;

public interface BranchService {
	
	
	BranchDto createBranch(BranchDto branchDto) throws UserException;
	BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception;
	void deleteBranch(Long id) throws Exception;
	List<BranchDto> getAllBranchByStoreId(Long StoreId);
	BranchDto getBranchById(Long id) throws Exception;
	
	

}
