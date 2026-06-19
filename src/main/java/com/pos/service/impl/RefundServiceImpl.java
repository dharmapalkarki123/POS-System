package com.pos.service.impl;

import com.pos.exception.UserException;
import com.pos.mapper.RefundMapper;
import com.pos.modal.Branch;
import com.pos.modal.Order;
import com.pos.modal.Refund;
import com.pos.modal.Users;
import com.pos.payload.dto.RefundDto;
import com.pos.repository.OrderRepository;
import com.pos.repository.RefundRepository;
import com.pos.service.RefundService;
import com.pos.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class RefundServiceImpl implements RefundService {

    private UserService userService;
    private RefundRepository refundRepository;
    private OrderRepository orderRepository;

    public RefundServiceImpl(UserService userService, RefundRepository refundRepository, OrderRepository orderRepository) {
        this.userService = userService;
        this.refundRepository = refundRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public RefundDto createRefund(RefundDto refund) throws UserException {

        Users cashier=userService.getCurrentUser();

        Order order=orderRepository.findById(refund.getOrder().getId()).orElseThrow(
                ()-> new UserException("Order not found")
        );

        Branch branch=order.getBranch();

        Refund createRefund=Refund.builder()
                .order(order)
                .cashier(cashier)
                .branch(branch)
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .createdAt(refund.getCreatedAt())
                .build();

        Refund savedRefund=refundRepository.save(createRefund);

        return RefundMapper.toDto(savedRefund);
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
