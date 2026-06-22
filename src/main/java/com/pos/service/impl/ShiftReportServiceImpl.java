package com.pos.service.impl;

import com.pos.mapper.ShiftReportMapper;
import com.pos.modal.*;
import com.pos.payload.dto.shiftReportDto;
import com.pos.repository.OrderRepository;
import com.pos.repository.RefundRepository;
import com.pos.repository.ShiftReportRepository;
import com.pos.service.ShiftReportService;
import com.pos.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShiftReportServiceImpl implements ShiftReportService {

    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;
    private ShiftReportRepository shiftReportRepository;
    private UserService userService;

    public ShiftReportServiceImpl(ShiftReportRepository shiftReportRepository, UserService userService, RefundRepository refundRepository, OrderRepository orderRepository) {
        this.shiftReportRepository = shiftReportRepository;
        this.userService = userService;
        this.refundRepository = refundRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public shiftReportDto startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception {


        Users currentUser=userService.getCurrentUser();

        shiftStart=LocalDateTime.now();
        LocalDateTime satrtOfDay=shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay=shiftStart.withHour(23).withMinute(59).withSecond(0);

        Optional<ShiftReport> existing =shiftReportRepository.findByCashierAndShiftStartBetween(
                currentUser, satrtOfDay, endOfDay
        );

        if(existing.isPresent()){

            throw new Exception("Shift already started");

        }

        Branch branch=currentUser.getBranch();

        ShiftReport shiftReport=ShiftReport.builder()
                .cashier(currentUser)
                .shiftStart(shiftStart)
                .branch(branch)

                .build();

        ShiftReport savedShiftReport=shiftReportRepository.save(shiftReport);


        return ShiftReportMapper.toDto(savedShiftReport);
    }

    @Override
    public shiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {

        Users currentUser=userService.getCurrentUser();

        ShiftReport shiftReport=shiftReportRepository.findByTopCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser)
                .orElseThrow(()->new Exception("Shift not found"));

        shiftReport.setShiftEnd(shiftEnd);

        List<Refund> refunds=refundRepository.findByCashierIdAndCreatedAtBetween(currentUser.getId(),
                shiftReport.getShiftStart(), shiftReport.getShiftEnd());

        double totalRefund=refunds.stream().mapToDouble(
                refund ->refund.getAmount()!=null?
                        refund.getAmount():0.0).sum();

        List<Order>  orders=orderRepository.findByCashierAndCreatedAtBetween(
                currentUser,shiftReport.getShiftStart(), shiftReport.getShiftEnd()
        );

        double totalSales=orders.stream().mapToDouble(
                Order::getTotalAmount
        ).sum();

        int totalOrders=orders.size();

        double netSales=totalSales-totalRefund;

        shiftReport.setTotalRefunds(totalRefund);
        shiftReport.setTotalSale(totalSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setNetSale(netSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummary(getPaymentSummary(orders,totalSales));
        shiftReport.setRefunds(refunds);

        return null;
    }



    @Override
    public shiftReportDto getShiftReportId(Long id) {
        return null;
    }

    @Override
    public List<shiftReportDto> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<shiftReportDto> getShiftReportsByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<shiftReportDto> getShiftReportByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public shiftReportDto getCurrentShiftReportProgress(Long cashierId) throws Exception {
        return null;
    }

    @Override
    public shiftReportDto getShiftReportByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        return null;
    }

    private List<PaymentSummary> getPaymentSummary(List<Order> orders, double totalSales) {

        Map<PaymentType, List<Order>> grouped = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getPaymentType() != null
                        ? order.getPaymentType()
                        : PaymentType.CASH));

        List<PaymentSummary> summaries = new ArrayList<>();

        for (Map.Entry<PaymentType, List<Order>> entry : grouped.entrySet()) {

            double amount = entry.getValue().stream()
                    .mapToDouble(Order::getTotalAmount)
                    .sum();

            int transactions = entry.getValue().size();

            double percent = (amount / totalSales) * 100;

            PaymentSummary ps = new PaymentSummary();

            ps.setPaymentType(entry.getKey());
            ps.setTotalAmount(amount);
            ps.setTransactionCount(transactions);
            ps.setPercentage(percent);

            summaries.add(ps);
        }

        return summaries;



    }

    private List<Product> getTopSellingProducts(List<Order> orders) {

        Map<Product,Integer> productSalesMap=new HashMap<>();

        for(Order  order:orders){

            for(OrderItems item:order.getItems()){
                Product product=item.getProduct();
                productSalesMap.put(product,
                        productSalesMap.getOrDefault(product,0)+ item.getQuantity());
            }

        }
        return  productSalesMap.entrySet().stream()
                .sorted((a,b)->b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }

    private List<Order> getRecentOrders(List<Order> orders) {

        return orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .limit(5)
                .collect(Collectors.toList());

    }
}
