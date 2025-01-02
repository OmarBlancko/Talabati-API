package com.example.talabati.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.talabati.controller.Exceptions.OrderNotFoundException;
import com.example.talabati.model.Order;
import com.example.talabati.repositories.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @SuppressWarnings("UseSpecificCatch")
    public Order createOrder(Order order) {
        try {

            if (order == null) {
                throw new IllegalArgumentException("Order cannot be null");
            }
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating order: " + e.getMessage(), e);

        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public Order UpdateOrder(Order order, Long id) {
        try {
            if (order == null) {
                throw new IllegalArgumentException("Order cannot be null");
            }
            Order existingOrder = getOrderById(id);
            order.setId(id);
            if (order.getUserId() != 0) {
                existingOrder.setUserId(order.getUserId());
            }
            if (order.getOrderItems() != null) {
                existingOrder.setOrderItems(order.getOrderItems());
            }
            if (order.getDeliveryAddress() != null) {
                existingOrder.setDeliveryAddress(order.getDeliveryAddress());
            }
            if (order.getTotalPrice() != 0) {
                existingOrder.setTotalPrice(order.getTotalPrice());
            }
            if (order.getStatus() != null) {
                existingOrder.setStatus(order.getStatus());
            }
            if (order.getPayment() != null) {
                existingOrder.setPayment(order.getPayment());
            }
            if (order.getDeliveryId() != 0) {
                existingOrder.setDeliveryId(order.getDeliveryId());
            }

            return orderRepository.save(existingOrder);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating order: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public Order getOrderById(long id) {
        try {
            Optional<Order> existingOrder = orderRepository.findById(id);
            if (existingOrder.isEmpty()) {
                throw new OrderNotFoundException("Order with ID " + id + " not found.");
            }
            return existingOrder.get();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching order: " + e.getMessage(), e);
        }

    }

    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching orders: " + e.getMessage(), e);
        }
    }

    public Optional<List<Order>> getUserOrders(long userId) {
        try {
            return orderRepository.findOrdersByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching orders for user: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public void deleteOrder(long id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            if (order.isEmpty()) {
                throw new OrderNotFoundException("Order with ID " + id + " not found.");
            }
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting order: " + e.getMessage(), e);
        }

    }

}
