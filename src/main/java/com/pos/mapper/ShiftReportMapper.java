package com.pos.mapper;

import com.pos.modal.Order;
import com.pos.modal.Product;
import com.pos.modal.Refund;
import com.pos.modal.ShiftReport;
import com.pos.payload.dto.OrderDto;
import com.pos.payload.dto.ProductDto;
import com.pos.payload.dto.RefundDto;
import com.pos.payload.dto.shiftReportDto;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {

    public static shiftReportDto toDto(ShiftReport entity) {

        return shiftReportDto.builder()
                .id(entity.getId())
                .shiftStart(entity.getShiftStart())
                .shiftEnd(entity.getShiftEnd())
                .totalSale(entity.getTotalSale())
                .totalRefunds(entity.getTotalRefunds())
                .totalOrders(entity.getTotalOrders())
                .netSale(entity.getNetSale())
                .cashier(UserMapper.toDTO(entity.getCashier()))
                .cashierId(entity.getCashier().getId())
                .branchId(entity.getBranch().getId())
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .topSellingProducts(mapProduct(entity.getTopSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummary(entity.getPaymentSummary())
                .build();

    }

    private static List<RefundDto> mapRefunds(List<Refund> refunds) {

        if(refunds==null||refunds.isEmpty()){
            return null;
        }
        return  refunds.stream().map(RefundMapper::toDto).collect(Collectors.toList());

    }

    private static List<ProductDto> mapProduct(List<Product> topSellingProducts) {
        if(topSellingProducts==null||topSellingProducts.isEmpty()){
            return null;
        }
        return topSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());


    }

    private static List<OrderDto> mapOrders(List<Order> recentOrders) {
        if(recentOrders==null||recentOrders.isEmpty()){
            return null;
        }
        return recentOrders.stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

}
