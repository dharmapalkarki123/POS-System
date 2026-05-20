package com.pos.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pos.exception.UserException;
import com.pos.mapper.BranchMapper;
import com.pos.modal.Branch;
import com.pos.modal.Store;
import com.pos.modal.Users;
import com.pos.payload.dto.BranchDto;
import com.pos.repository.BranchRepository;
import com.pos.repository.StoreRepository;
import com.pos.service.BranchService;
import com.pos.service.UserService;




@Service
public class BranchServiceImpl implements BranchService{
	
	private BranchRepository branchRepository;
	
	private StoreRepository storeRepository;
	
	private UserService userService;
	
	public BranchServiceImpl(BranchRepository branchRepository, 
			StoreRepository storeRepository,
			UserService userService) {
		this.branchRepository=branchRepository;
		this.storeRepository=storeRepository;
		this.userService=userService;
		
		
	}
	

	@Override
	public BranchDto createBranch(BranchDto branchDto, Users user) throws UserException {
		
		Users currentUser=userService.getCurrentUser();
		Store store=storeRepository.findByStoreAdminId(currentUser.getId());
		
		Branch branch=BranchMapper.toEntity(branchDto, store);
		
		Branch savedBranch=branchRepository.save(branch);
		
	
		
		return BranchMapper.toDTO(savedBranch);
		
		
		
		
		
	
	}

	@Override
	public BranchDto updateBranch(Long id, BranchDto branchDto, Users user) throws Exception {
		Branch existing =branchRepository.findById(id).orElseThrow(
				()-> new Exception("Branch Not found")
				);
		
		existing.setName(branchDto.getName());
		existing.setWorkingDays(branchDto.getWorkingDays());
		existing.setEmail(branchDto.getEmail());
		existing.setPhone(branchDto.getPhone());
		existing.setAddress(branchDto.getAddress());
		existing.setOpenTime(branchDto.getOpenTime());
		existing.setCloseTime(branchDto.getCloseTime());
		existing.setUpdatedAt(LocalDateTime.now());
		
		Branch updatedBranch=branchRepository.save(existing);
		
		
		return BranchMapper.toDTO(updatedBranch);
	}

	@Override
	public void deleteBranch(Long id) throws Exception {
		Branch existing =branchRepository.findById(id).orElseThrow(
				()-> new Exception("Branch not Found"));
		
				branchRepository.delete(existing);
		
	}

	@Override
	public List<BranchDto> getAllBranchByStoreId(Long StoreId) {
		List<Branch> branches=branchRepository.findByStoreId(StoreId);
		return branches
				.stream()
				.map(BranchMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public BranchDto getBranchById(Long id) throws Exception {
		Branch existing=branchRepository.findById(id).orElseThrow(
				()-> new Exception("branch not exist")
				);
				
		return BranchMapper.toDTO(existing);
	}

}
