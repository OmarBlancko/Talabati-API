package com.example.talabati.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.talabati.controller.Exceptions.OrderNotFoundException;
import com.example.talabati.controller.Exceptions.PaymentNotFoundException;
import com.example.talabati.model.Order;
import com.example.talabati.model.Payment;
import com.example.talabati.service.OrderService;
import com.example.talabati.service.PaymentService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;

    /// POST Method
    @PostMapping
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        try {
            orderService.createOrder(order);
            Payment payment = order.getPayment();
            payment.setOrderId(order.getId());
            paymentService.createOrUpdatePayment(order.getPayment());
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Created Sucessfully");
        } catch (PaymentNotFoundException pe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment  Error " + pe.getMessage());
        } catch (OrderNotFoundException oe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order  Error " + oe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error in creating order: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {
        try {
            orderService.UpdateOrder(order, id);
            return ResponseEntity.status(HttpStatus.OK).body("Order Updated successfully");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updated order >>" + e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<?> getOrdersById(@RequestParam(value = "id", required = false) Long id) {
        try {
            if (id != null) {
                System.out.println(id.toString());
                Order response = orderService.getOrderById(id);
                if (response != null) {
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(404).body("Order Not found");
                }
            } else {
                List<Order> orders = orderService.getAllOrders();
                if (!orders.isEmpty()) {
                    return ResponseEntity.ok(orders);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable("userId") long userId) {
        try {

            Optional<List<Order>> response = orderService.getUserOrders(userId);
            if (response.isPresent() && ! response.isEmpty() != true) {
                return ResponseEntity.ok(response.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order Deleted successfully");

        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error in deleting order {Order not found} >>  " + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in deleting order >>  " + e.getMessage());

        }

    }

}