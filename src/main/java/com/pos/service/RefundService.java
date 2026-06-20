package com.pos.service;

import com.pos.exception.UserException;
import com.pos.modal.Refund;
import com.pos.payload.dto.RefundDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {

    RefundDto createRefund(RefundDto refund) throws UserException;

    List<RefundDto> getAllRefund() throws Exception;
    List<RefundDto> getRefundCashier(Long cashierId) throws Exception;
    List<RefundDto> getRefundByShiftReport(Long shiftReportId) throws Exception;
    List<RefundDto> getAllRefundByCashierAndDateRange(Long cashierId,
                                                      LocalDateTime startDate,
                                                      LocalDateTime endDate
                                              ) throws Exception;



    List<RefundDto> getRefundByBranch(Long branchId) throws Exception;
    RefundDto getRefundById(Long refundId) throws Exception;

    void deleteRefund(Long refundId) throws Exception;


}
