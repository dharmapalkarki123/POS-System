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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double totalAmount;


    private LocalDateTime createdAt;

    @ManyToOne
    private Branch branch;

      @ManyToOne
     private Users cashier;

      @ManyToOne
      private Customer customer;

      @OneToMany
      private List<OrderItems> items;


}
