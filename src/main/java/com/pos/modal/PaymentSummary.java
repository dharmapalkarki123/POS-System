package com.pos.modal;

import lombok.Data;

@Data
public class PaymentSummary {

    private PaymentType paymentType;

    private Double totalAmount;

    private int transactionCount;

    private double percentage;


}
