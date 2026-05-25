package com.pos.service.impl;

import com.pos.mapper.UserMapper;
import com.pos.modal.Branch;
import com.pos.modal.Store;
import com.pos.modal.UserRole;
import com.pos.modal.Users;
import com.pos.payload.dto.userDto;
import com.pos.repository.BranchRepository;
import com.pos.repository.StoreRepository;
import com.pos.repository.UsersRepository;
import com.pos.service.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;


    public EmployeeServiceImpl(StoreRepository storeRepository, BranchRepository branchRepository, PasswordEncoder passwordEncoder, UsersRepository usersRepository) {

        this.storeRepository = storeRepository;
        this.branchRepository = branchRepository;
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public userDto createStoreEmployee(userDto employee, Long storeId) throws Exception {

        Store store=storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("Store not found")
        );

        Branch branch=null;

        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            if(employee.getBranchId()==null){

                throw new Exception("Branch id is required");

            }

            branch=branchRepository.findById(employee.getBranchId()).orElseThrow(
                    () -> new Exception("Branch not found")
            );
        }

        Users user= UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));
        Users savedEmployee=usersRepository.save(user);
        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER &&branch!=null){
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }

        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public userDto createBranchEmployee(userDto employee, Long branchId) throws Exception {
        Branch branch=branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found")
        );

        if(employee.getRole()==UserRole.ROLE_BRANCH_CASHIER||
        employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            Users user=UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDTO(usersRepository.save(user));
        }

        throw new Exception("Branch role not supported");
    }

    @Override
    public Users updateEmployee(Long employeeId, userDto employeeDetails) throws Exception {

        Users existingUser=usersRepository.findById(employeeId).orElseThrow(
                ()->new Exception("User Not found")
        );

        Branch branch=branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(
                ()->new Exception("Branch not found ")
        );

        existingUser.setEmail(employeeDetails.getEmail());
        existingUser.setFullName(employeeDetails.getFullName());
        existingUser.setPassword(employeeDetails.getPassword());
        existingUser.setRole(employeeDetails.getRole());
        existingUser.setBranch(branch);

      usersRepository.save(existingUser);




        return usersRepository.save(existingUser);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        Users user=usersRepository.findById(employeeId).orElseThrow(
                ()->new Exception("user not found")
        );

        usersRepository.delete(user);

    }

    @Override
    public List<userDto> findStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store=storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("Store not found")
        );



        return usersRepository.findByStore(store).
                stream().filter(
                        user->role==null ||user.getRole()==role
                ).map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<userDto> findBranchEmployees(Long branchId, UserRole role) throws Exception {

        Branch branch=branchRepository.findById(branchId).orElseThrow(
                ()->new Exception("Branch not found ")
        );

        List<userDto> employee=usersRepository.findByBranchId(branchId).
                stream().filter(
                        user->role==null ||user.getRole()==role
                ).map(UserMapper::toDTO).collect(Collectors.toList());

        return employee;
    }
}
