package com.pos.controller;

import com.pos.modal.UserRole;
import com.pos.modal.Users;
import com.pos.payload.dto.userDto;
import com.pos.payload.response.ApiResponse;
import com.pos.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/store/{storeId}")
    public ResponseEntity<userDto> createStoreEmployee(@PathVariable Long storeId,
                                                       @RequestBody userDto UserDto) throws Exception {

        userDto employee=employeeService.createStoreEmployee(UserDto,storeId);

        return ResponseEntity.ok(employee);

    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<userDto> createBranchesEmployee(@PathVariable Long branchId,
                                                       @RequestBody userDto UserDto) throws Exception {

        userDto employee=employeeService.createBranchEmployee(UserDto,branchId);

        return ResponseEntity.ok(employee);

    }

    @PutMapping("{id}")
    public ResponseEntity<Users> updatedBranchesEmployee(@PathVariable Long id,
                                                          @RequestBody userDto UserDto) throws Exception {

        Users employee=employeeService.updateEmployee(id,UserDto);

        return ResponseEntity.ok(employee);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> updatedBranchesEmployee(@PathVariable Long id

                                                               ) throws Exception {

        employeeService.deleteEmployee(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Successfully deleted employee");




        return ResponseEntity.ok(apiResponse);

    }



    @GetMapping("/store/{id}")
    public ResponseEntity<List<userDto>> findStoreEmployees(@PathVariable Long id,
                                                            @RequestParam (required = false) UserRole userRole ) throws Exception {

        List<userDto> employees=employeeService.findStoreEmployees(id,userRole);
        return ResponseEntity.ok(employees);

    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<userDto>> findBranchEmployees(@PathVariable Long id,
                                                          @RequestParam (required = false) UserRole userRole ) throws Exception {

        List<userDto> employees=employeeService.findBranchEmployees(id,userRole);
        return ResponseEntity.ok(employees);

    }



}
