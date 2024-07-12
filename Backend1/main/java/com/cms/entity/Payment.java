package com.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PaymentId", length=20)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "OrderId")
    @JsonIgnoreProperties("payments")
    private OrderPlace order;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    @JsonIgnoreProperties("payments")
    private Customer customer;

    @Column(name="PaymentDate", length= 20)
    private LocalDate paymentDate;

    @Column(name="PaymentAmount", length= 20)
    private Double paymentAmount;

    @Column(name="PaymentMethod", length= 20)
    private String paymentMethod;

    @Column(name="PaymentStatus", length= 20)
    private String paymentStatus;
}
