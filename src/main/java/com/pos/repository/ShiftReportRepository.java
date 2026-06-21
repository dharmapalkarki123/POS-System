package com.pos.repository;

import com.pos.modal.ShiftReport;
import com.pos.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReport, Long> {

  List<ShiftReport> findByCashierId(Long id);
  List<ShiftReport> findByBranchId(Long id);

  Optional<ShiftReport> findByTopCashierAndShiftEndIsNullOrderByShiftStartDesc(
          Users cashier);

  Optional<ShiftReport> findByCashierAndShiftStartBetween(Users cashier,
                                                          LocalDateTime start,
                                                          LocalDateTime end);
}