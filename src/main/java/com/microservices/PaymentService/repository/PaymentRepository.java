package com.microservices.PaymentService.repository;

import com.microservices.PaymentService.entity.Payment;
import com.microservices.PaymentService.model.PaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(Long orderId);
}
