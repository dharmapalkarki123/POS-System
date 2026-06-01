package com.pos.service.impl;

import com.pos.modal.OrderStatus;
import com.pos.modal.PaymentType;
import com.pos.payload.dto.OrderDto;
import com.pos.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus status) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return List.of();
    }

    @Override
    public void deleteOrder(Long id) throws Exception {

    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception {
        return List.of();
    }
}
