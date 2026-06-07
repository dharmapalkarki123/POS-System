package com.pos.controller;

import com.pos.exception.UserException;
import com.pos.modal.OrderStatus;
import com.pos.modal.PaymentType;
import com.pos.payload.dto.OrderDto;
import com.pos.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws UserException {

        return ResponseEntity.ok(orderService.createOrder(orderDto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(id));

    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrderBranch(@PathVariable Long branchId,
                                                         @RequestParam(required=false) Long customerId,
                                                         @RequestParam(required = false) Long cashierId,
                                                         @RequestParam(required = false)PaymentType paymentType,
                                                         @RequestParam(required = false)OrderStatus status
                                                         ) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId,customerId,cashierId,paymentType,status));

    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByCahier(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCashier(id));

    }

    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDto>> getTodayOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));

    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDto>> getCustomerOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));

    }
    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDto>> getRecentOrder(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));

    }






}
