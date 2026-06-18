package com.pos.service.impl;

import com.pos.modal.Refund;
import com.pos.payload.dto.RefundDto;
import com.pos.service.RefundService;

import java.time.LocalDateTime;
import java.util.List;

public class RefundServiceImpl implements RefundService {
    @Override
    public RefundDto createRefund(Refund refund) {
        return null;
    }

    @Override
    public List<RefundDto> getAllRefund() throws Exception {
        return List.of();
    }

    @Override
    public RefundDto getRefundCashier(Long cashierId) throws Exception {
        return null;
    }

    @Override
    public RefundDto getRefundByShiftReport(Long shiftReportId) throws Exception {
        return null;
    }

    @Override
    public List<RefundDto> getAllRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return List.of();
    }

    @Override
    public List<RefundDto> getRefundByBranch(Long branchId) throws Exception {
        return List.of();
    }

    @Override
    public RefundDto getRefundById(Long refundId) throws Exception {
        return null;
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {

    }
}
