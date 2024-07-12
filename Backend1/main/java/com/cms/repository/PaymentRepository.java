package com.cms.repository;

import java.util.List;

import com.cms.entity.Payment;

public interface PaymentRepository {
	List<Payment> findByCustomer_CustomerID(Long customerID);
    List<Payment> findByOrder_OrderID(Long orderID);
    List<Payment> findByPaymentStatus(String paymentStatus);
    List<Payment> findByPaymentMethod(String paymentMethod);
}
