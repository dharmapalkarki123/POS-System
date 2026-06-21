package com.pos.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private Double totalSale;
    private Double totalRefunds;

    private Double netSale;
    private Double totalOrders;

    @ManyToOne
    private Users cashier;



    @ManyToOne
    private Branch branch;

    @Transient
    private List<PaymentSummary> paymentSummary;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> topSellingProducts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> recentOrders;

    @OneToMany(mappedBy = "shiftReport",cascade = CascadeType.ALL)
    private List<Refund> refunds;









}
