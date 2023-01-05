package com.kruger.ps.service;

import com.kruger.ps.entity.Payment;
import com.kruger.ps.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public Payment doPayment(Payment payment)  {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }
    public String paymentProcessing(){
        //api should be 3rd party payment gateway (paypal,paytm...)
        return new Random().nextBoolean()?"success":"false";
    }
    public Payment findPaymentHistoryByOrderId(int orderId) {
        Payment payment=repository.findByOrderId(orderId);
        return payment ;
    }
}