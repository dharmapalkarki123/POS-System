package com.pos.service;

import com.pos.exception.UserException;
import com.pos.modal.OrderStatus;
import com.pos.modal.PaymentType;
import com.pos.payload.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto) throws UserException;
    OrderDto getOrderById(Long id) throws  Exception;

    List<OrderDto> getOrdersByBranch(Long branchId,
                                     Long customerId,
                                     Long cashierId,
                                     PaymentType paymentType,
                                     OrderStatus status) throws Exception;

List<OrderDto> getOrderByCashier(Long cashierId);
void deleteOrder(Long id) throws Exception;

List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception;

List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception;

List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception;

}
