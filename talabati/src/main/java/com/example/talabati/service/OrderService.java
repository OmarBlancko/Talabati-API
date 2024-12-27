package com.example.talabati.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.talabati.model.Order;
import com.example.talabati.repositories.OrderRepository;

@Service

class OrderService {

    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }

    Optional<List<Order>> getUserOrders(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

}
