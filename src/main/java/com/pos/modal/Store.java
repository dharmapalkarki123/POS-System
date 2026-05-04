package com.pos.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Store {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( nullable = false)
    private String brand;

    @OneToOne
    private Users storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private storeContact contact=new storeContact();

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        status=StoreStatus.PENDING;

    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }


}
