package com.microservices.PaymentService.controller;

import com.microservices.PaymentService.entity.Payment;
import com.microservices.PaymentService.model.PaymentRequest;
import com.microservices.PaymentService.model.PaymentResponse;
import com.microservices.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/request")
    public ResponseEntity<Long> createPayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<PaymentResponse>paymentDetailsByOrderId(@PathVariable Long orderId) {
        return new ResponseEntity<>(paymentService.paymentDetailsByOrder(orderId), HttpStatus.OK);
    }
}
