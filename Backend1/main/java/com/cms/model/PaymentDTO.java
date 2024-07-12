package com.cms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.Order;

import com.cms.entity.Customer;

public class PaymentDTO {
	 
	private Long paymentId;
    @ManyToOne
	private Order order;
    @ManyToOne
    private Customer customer;
    private LocalDate paymentDate;
    private Double paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
}
