package com.example.talabati.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talabati.controller.Exceptions.PaymentNotFoundException;
import com.example.talabati.model.Payment;
import com.example.talabati.repositories.PaymentRepository;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService() {
    }
    @Autowired

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createOrUpdatePayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("payment cannot be null");
        }
        return paymentRepository.save(payment);
    }

    public Payment findPaymentById(long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));
    }
    public Payment findPaymentByOrderId(long orderId) {
        return paymentRepository.findPaymentByOrderId(orderId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + orderId));    }

    public void deletePayment(Payment payment) {
        paymentRepository.delete(payment);
    }
}
