package com.example.talabati.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talabati.Exceptions.PaymentNotFoundException;
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

    public Payment createPayment(Payment payment) {
        
        try {
            if (payment == null) {
                throw new IllegalArgumentException("payment cannot be null");
            }
            return paymentRepository.save(payment);
        } catch (Exception e) {
            throw new RuntimeException("Error in create payment >>> " + e.getMessage());
        }

    }

    public Payment updatePayment(Payment newPayment, Long id) {
        try {
            Payment existingPayment = findPaymentById(id);

            if (newPayment.getAmount() > 0) {
                existingPayment.setAmount(newPayment.getAmount());
            }
            if (newPayment.getPaymentMethod() != null && !newPayment.getPaymentMethod().isEmpty()) {
                existingPayment.setPaymentMethod(newPayment.getPaymentMethod());
            }
            if (newPayment.getStatus() != null && !newPayment.getStatus().isEmpty()) {
                existingPayment.setStatus(newPayment.getStatus());
            }
            if (newPayment.getTransactionId() != null && !newPayment.getTransactionId().isEmpty()) {
                existingPayment.setTransactionId(newPayment.getTransactionId());
            }
            if(newPayment.getOrderId() !=0 ){
                existingPayment.setOrderId(newPayment.getOrderId());
            }
            if(newPayment.getCreatedAt()  != null ) {
                existingPayment.setCreatedAt(LocalDateTime.now());
            }
            return paymentRepository.save(existingPayment);

        } catch (Exception e) {
            throw new RuntimeException("Error while updating payment  >" + e.getMessage());
        }
    }

    public Payment findPaymentById(long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));
    }

    public Payment findPaymentByOrderId(long orderId) {
        return paymentRepository.findPaymentByOrderId(orderId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with order ID: " + orderId));
    }

    public void deletePayment(Payment payment) {
        paymentRepository.delete(payment);
    }
}
