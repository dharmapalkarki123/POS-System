package com.pos.service.impl;

import com.pos.exception.UserException;
import com.pos.modal.*;
import com.pos.payload.dto.OrderDto;
import com.pos.repository.OrderRepository;
import com.pos.repository.ProductRepository;
import com.pos.service.OrderService;
import com.pos.service.UserService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserService userService;
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws UserException {
        Users cashier=userService.getCurrentUser();

        Branch branch=cashier.getBranch();
        if(branch==null){
            throw new UserException("cashier's Branch not found");
        }

        Order order=Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDto.getCustomer())
                .paymentType(orderDto.getPaymentType())
                .build();

        List<OrderItems> orderItems=orderDto.getItems().stream().map(
                itemDto->{
                    Product product=productRepository.findById(itemDto.getProductId())
                            .orElseThrow(()->new EntityNotFoundException("Product not found"));

                    return OrderItems.builder()
                            .product(product)
                            .quantity(itemDto.getQuantity())
                            .price(product.getSellingPrice()*itemDto.getQuantity())
                            .order(order)
                            .build();

                }
        ).toList();

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
