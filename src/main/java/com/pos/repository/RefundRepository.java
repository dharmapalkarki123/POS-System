package com.pos.repository;

import com.pos.modal.Refund;
import com.pos.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByCashierAndCreatedAtBetween(
            Users cashier,
            LocalDateTime from,
            LocalDateTime to
    );

    List<Refund> findByCashierId(Long id);
    List<Refund> findByShiftReport(Long id);
    List<Refund> findByBranchId(Long id);


}