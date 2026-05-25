package com.pos.service;

import com.pos.modal.UserRole;
import com.pos.modal.Users;
import com.pos.payload.dto.userDto;

import java.util.List;

public interface EmployeeService {

    userDto createStoreEmployee(userDto employee,Long storeId) throws Exception;
    userDto createBranchEmployee(userDto employee,Long branchId) throws Exception;
    Users updateEmployee(Long employeeId, userDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<userDto> findStoreEmployees(Long storeId, UserRole role) throws Exception;
    List<userDto> findBranchEmployees(Long branchId, UserRole role) throws Exception;

}
