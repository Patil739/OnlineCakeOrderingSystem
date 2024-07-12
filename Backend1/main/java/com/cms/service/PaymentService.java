package com.cms.service;

import java.util.List;
import java.util.Optional;

import com.cms.entity.Payment;

public interface PaymentService {
	 Payment savePayment(Payment payment);
	    Optional<Payment> getPaymentById(Long paymentId);
	    List<Payment> getAllPayments();
	    Payment updatePayment(Long paymentId, Payment payment);
	    void deletePayment(Long paymentId);

}
