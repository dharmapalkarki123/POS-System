package com.pos.service;

import com.pos.exception.UserException;
import com.pos.payload.dto.shiftReportDto;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    shiftReportDto startShift() throws Exception;
    shiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd)
            throws Exception;

    shiftReportDto getShiftReportId(Long id) throws Exception;

    List<shiftReportDto> getAllShiftReports();

    List<shiftReportDto> getShiftReportsByBranchId(Long branchId);
    List<shiftReportDto> getShiftReportByCashierId(Long cashierId);

    shiftReportDto getCurrentShiftReportProgress(Long cashierId) throws Exception;

    shiftReportDto getShiftReportByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;


}
