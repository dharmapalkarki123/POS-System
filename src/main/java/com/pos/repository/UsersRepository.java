package com.pos.repository;

import com.pos.modal.Branch;
import com.pos.modal.Store;
import com.pos.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    List<Users> findByStore(Store store);
    List<Users> findByBranchId(Long branchId);
}
