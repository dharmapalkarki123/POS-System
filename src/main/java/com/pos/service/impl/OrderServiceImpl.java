package com.pos.service.impl;

import com.pos.exception.UserException;
import com.pos.mapper.OrderMapper;
import com.pos.modal.*;
import com.pos.payload.dto.OrderDto;
import com.pos.repository.OrderRepository;
import com.pos.repository.ProductRepository;
import com.pos.service.OrderService;
import com.pos.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
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

        double total=orderItems.stream().mapToDouble(
                OrderItems::getPrice
        ).sum();
        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder=orderRepository.save(order);

        return OrderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        return orderRepository.findById(id)
                .map(OrderMapper::toDto)
                .orElseThrow(
                ()-> new Exception("order not found with  " +id)
        );
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus status) throws Exception {
        return orderRepository.findByBranchId(branchId).stream()
                .filter(order->customerId==null||
                        (order.getCustomer()!=null &&
                                order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId==null ||
                        order.getCashier()!=null &&
                        order.getCashier().getId().equals(cashierId))
                .filter(order -> paymentType==null ||
                        order.getPaymentType()==paymentType)
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {


        return orderRepository.findByCashierId(cashierId).stream()
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) throws Exception {
        Order order=orderRepository.findById(id).orElseThrow(
                ()-> new Exception("order not found with  " +id)
        );

        orderRepository.delete(order);

    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception {
        LocalDate today=LocalDate.now();
        LocalDateTime start=today.atStartOfDay();
        LocalDateTime end=today.plusDays(1).atStartOfDay();
        return orderRepository.findByBranchIdAndCreatedAtBetween(
                branchId,start,end
        ).stream().map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception {
        return orderRepository.findByCustomerId(customerId)
                .stream().map(
                        OrderMapper::toDto
                ).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception {
        return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId)
                .stream().map(
                        OrderMapper::toDto

                ).collect(Collectors.toList());
    }
}
