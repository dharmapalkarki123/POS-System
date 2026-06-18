package com.pos.mapper;

import com.pos.modal.Refund;
import com.pos.payload.dto.OrderDto;
import com.pos.payload.dto.RefundDto;

public class RefundMapper {

    public static RefundDto toDto(Refund refund) {
        return RefundDto.builder()
                .id(refund.getId())
                .orderId(refund.getOrder().getId())
                .amount(refund.getAmount())
                .reason(refund.getReason())
                .cashierName(refund.getCashier().getFullName())
                .branchId(refund.getBranch().getId())
                .ShiftReportId(refund.getShiftReport().getId())
                .createdAt(refund.getCreatedAt())
                .build();


    }

}
