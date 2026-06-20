package com.pos.mapper;

import com.pos.modal.Refund;
import com.pos.payload.dto.OrderDto;
import com.pos.payload.dto.RefundDto;

public class RefundMapper {

    public static RefundDto toDto(Refund refund) {
        return RefundDto.builder()
                .id(refund.getId())
                .orderId(refund.getOrder() != null ? refund.getOrder().getId() : null)
                .amount(refund.getAmount())
                .reason(refund.getReason())
                .cashierName(refund.getCashier() != null ? refund.getCashier().getFullName() : null)
                .branchId(refund.getBranch() != null ? refund.getBranch().getId() : null)
                .shiftReportId(refund.getShiftReport() != null ? refund.getShiftReport().getId() : null)
                .createdAt(refund.getCreatedAt())
                .build();
    }
}
