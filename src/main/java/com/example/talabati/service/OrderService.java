package com.example.talabati.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talabati.Exceptions.OrderNotFoundException;
import com.example.talabati.model.ApiResponse;
import com.example.talabati.model.Order;
import com.example.talabati.model.OrderItem;
import com.example.talabati.model.Payment;
import com.example.talabati.repositories.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderItemsService orderItemsService;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ApiResponse<Order> createOrder(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        // save the order in DB
        orderRepository.save(order);
        // Handle Payment
        Payment payment = order.getPayment();
        payment.setOrderId(order.getId());
        Payment paymentResponse = paymentService.createPayment(payment);
        order.setPayment(paymentResponse);
        // Handle order Items 
        List<OrderItem> orderItemsList = order.getOrderItems();
        orderItemsList.forEach(orderItem -> orderItem.setOrder(order));
        List<OrderItem> itemsResponse = orderItemsService.createOrderItems(orderItemsList);
        order.setOrderItems(itemsResponse);
        // response return
        ApiResponse<Order> response = new ApiResponse<>(200, "Order Created Successfully ", order);

        return response;
    }

    public ApiResponse<Order> UpdateOrder(Order order, Long id) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        Order existingOrder = getOrderById(id);
        order.setId(id);
         // Check if there's empty fields in the request
        checkRequestValidity(order,existingOrder );
        // update payment via payment service
        if (order.getPayment() != null) {
            Payment payment = order.getPayment();
            Payment existingPayment = paymentService.findPaymentByOrderId(id);
            payment.setOrderId(existingPayment.getOrderId());
            paymentService.updatePayment(payment, payment.getId());
        }
        // update order items via order items service
        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            List<OrderItem> incomingOrderItems = order.getOrderItems();
            List<OrderItem> existingOrderItems = orderItemsService.getItemsByOrderId(id);

            Map<Long, OrderItem> existingOrderItemsMap = existingOrderItems.stream()
                    .collect(Collectors.toMap(OrderItem::getId, item -> item));

            incomingOrderItems.forEach(incomingItem -> {
                if (incomingItem.getId() == 0) {
                    incomingItem.setOrder(order);
                    existingOrderItems.add(incomingItem);
                } else if (existingOrderItemsMap.containsKey(incomingItem.getId())) {
                    OrderItem existingItem = existingOrderItemsMap.get(incomingItem.getId());
                    existingItem.updateFrom(incomingItem);
                    existingOrderItemsMap.remove(incomingItem.getId());
                }
            });

            existingOrderItems.removeIf(item -> existingOrderItemsMap.containsKey(item.getId()));
        }

        existingOrder.updateFrom(order);
        orderRepository.save(existingOrder);
        ApiResponse<Order> response = new ApiResponse<>(200, "Order updated Successfully ", existingOrder);
        return response;
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found."));
    }

    public ApiResponse<List<Order>> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.isEmpty()) {

            return new ApiResponse<>(204, "No orders found !", new ArrayList<>());
        }

        return new ApiResponse<List<Order>>(200, "Orders fetched successfully  with  associated user", orderList);
    }

    public ApiResponse<List<Order>> getUserOrders(long userId) {
        List<Order> ordersList = orderRepository.findOrdersByUserId(userId);
        if (ordersList.isEmpty()) {
            return new ApiResponse<List<Order>>(204, "No orders found with  associated user", new ArrayList<>());
        }
        return new ApiResponse<List<Order>>(200, "Orders fetched successfully  with  associated user", ordersList);
    }

    public void deleteOrder(long id) {
        Order order = getOrderById(id); // Will throw OrderNotFoundException if not found
        orderRepository.deleteById(order.getId());
    }
public void checkRequestValidity(Order updatedOrder,Order existingOrder) {
    if (updatedOrder.getUserId() != 0) {
        existingOrder.setUserId(updatedOrder.getUserId());
    }
    if (updatedOrder.getOrderItems() != null) {
        existingOrder.setOrderItems(updatedOrder.getOrderItems());
    }
    if (updatedOrder.getDeliveryAddress() != null) {
        existingOrder.setDeliveryAddress(updatedOrder.getDeliveryAddress());
    }
    if (updatedOrder.getTotalPrice() != 0) {
        existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
    }
    if (updatedOrder.getStatus() != null) {
        existingOrder.setStatus(updatedOrder.getStatus());
    }
    if (updatedOrder.getPayment() != null) {
        existingOrder.setPayment(updatedOrder.getPayment());
    }
    if (updatedOrder.getDeliveryId() != 0) {
        existingOrder.setDeliveryId(updatedOrder.getDeliveryId());
    }

}
}
