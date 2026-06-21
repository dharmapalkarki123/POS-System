package com.pos.service.impl;

import com.pos.mapper.ShiftReportMapper;
import com.pos.modal.Branch;
import com.pos.modal.Refund;
import com.pos.modal.ShiftReport;
import com.pos.modal.Users;
import com.pos.payload.dto.shiftReportDto;
import com.pos.repository.RefundRepository;
import com.pos.repository.ShiftReportRepository;
import com.pos.service.ShiftReportService;
import com.pos.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftReportServiceImpl implements ShiftReportService {

    private final RefundRepository refundRepository;
    private ShiftReportRepository shiftReportRepository;
    private UserService userService;

    public ShiftReportServiceImpl(ShiftReportRepository shiftReportRepository, UserService userService, RefundRepository refundRepository) {
        this.shiftReportRepository = shiftReportRepository;
        this.userService = userService;
        this.refundRepository = refundRepository;
    }

    @Override
    public shiftReportDto startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception {


        Users currentUser=userService.getCurrentUser();

        shiftStart=LocalDateTime.now();
        LocalDateTime satrtOfDay=shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay=shiftStart.withHour(23).withMinute(59).withSecond(0);

        Optional<ShiftReport> existing =shiftReportRepository.findByCashierAndShiftStartBetween(
                currentUser, satrtOfDay, endOfDay
        );

        if(existing.isPresent()){

            throw new Exception("Shift already started");

        }

        Branch branch=currentUser.getBranch();

        ShiftReport shiftReport=ShiftReport.builder()
                .cashier(currentUser)
                .shiftStart(shiftStart)
                .branch(branch)

                .build();

        ShiftReport savedShiftReport=shiftReportRepository.save(shiftReport);


        return ShiftReportMapper.toDto(savedShiftReport);
    }

    @Override
    public shiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {

        Users currentUser=userService.getCurrentUser();

        ShiftReport shiftReport=shiftReportRepository.findByTopCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser)
                .orElseThrow(()->new Exception("Shift not found"));

        shiftReport.setShiftEnd(shiftEnd);

        List<Refund> refunds=refundRepository.findByCashierIdAndCreatedAtBetween(currentUser.getId(),
                shiftReport.getShiftStart(), shiftReport.getShiftEnd());

        double totalRefund=refunds.stream().mapToDouble(
                refund ->refund.getAmount()!=null?
                        refund.getAmount():0.0).sum();


        return null;
    }

    @Override
    public shiftReportDto getShiftReportId(Long id) {
        return null;
    }

    @Override
    public List<shiftReportDto> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<shiftReportDto> getShiftReportsByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<shiftReportDto> getShiftReportByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public shiftReportDto getCurrentShiftReportProgress(Long cashierId) throws Exception {
        return null;
    }

    @Override
    public shiftReportDto getShiftReportByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        return null;
    }
}
