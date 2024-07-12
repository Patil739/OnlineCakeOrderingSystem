package com.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", length = 20)
    private Long orderID;

    @ManyToOne
    @JoinColumn(name = "AdminId", nullable = false)
    private Admin admin;

    @Column(name = "OrderDate")
    private Date orderDate;

    @Column(name = "DeliveryDate")
    private Date deliveryDate;

    @Column(name = "OrderStatus", length = 50)
    private String orderStatus;

    @Column(name = "TotalAmount")
    private Double totalAmount;

    //cart connect with order
   /* @JoinTable(
        name = "Order_Cakes",
        joinColumns = @JoinColumn(name = "OrderID"),
        inverseJoinColumns = @JoinColumn(name = "Cakesid")
    )
    @JsonIgnoreProperties("orders")
    private Set<Cakes> cakes;*/
}
