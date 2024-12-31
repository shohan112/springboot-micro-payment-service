package com.microservices.PaymentService.service;

import com.microservices.PaymentService.entity.Payment;
import com.microservices.PaymentService.model.PaymentModeEnum;
import com.microservices.PaymentService.model.PaymentRequest;
import com.microservices.PaymentService.model.PaymentResponse;
import com.microservices.PaymentService.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TransferQueue;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Request: {}", paymentRequest);

        Payment paymentDetails = Payment.builder()
                .paymentDate(Instant.now())
                .paymentModeEnum(paymentRequest.getPaymentModeEnum().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();
        paymentRepository.save(paymentDetails);
        log.info("Payment Complete with id: {}", paymentDetails.getId());
        return paymentDetails.getId();
    }

    @Override
    public PaymentResponse paymentDetailsByOrder(Long orderId) {
        log.info("Getting Payment Details by Order Id: {}", orderId);
        Payment paymentDetails = paymentRepository.findByOrderId(orderId);
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId(paymentDetails.getId())
                .paymentDate(paymentDetails.getPaymentDate())
                .paymentStatus(paymentDetails.getPaymentStatus())
                .amount(paymentDetails.getAmount())
                .paymentMode(PaymentModeEnum.valueOf(paymentDetails.getPaymentModeEnum()))
                .orderId(paymentDetails.getOrderId())
                .build();
        return paymentResponse;
    }
}
