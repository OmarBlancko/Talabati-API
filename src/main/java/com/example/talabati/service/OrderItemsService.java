package com.example.talabati.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.talabati.controller.Exceptions.OrderItemsNotFoundException;
import com.example.talabati.model.OrderItem;
import com.example.talabati.repositories.OrderItemRespository;

@Service
public class OrderItemsService {

    private final OrderItemRespository orderItemRespository;

    public OrderItemsService(OrderItemRespository orderItemRespository) {
        this.orderItemRespository = orderItemRespository;
    }

    public List<OrderItem> createOrderItems(List<OrderItem> orderItemList) {

        try {

            if (orderItemList.isEmpty()) {
                throw new IllegalArgumentException("Order items cannot be null !!");
            }
            return orderItemRespository.saveAll(orderItemList);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating order items : " + e.getMessage(), e);

        }
    }

    public OrderItem updateOrderItems(OrderItem orderItem, Long id) {
        try {
            if (orderItem == null) {
                throw new IllegalArgumentException("Order items cannot be null !!");
            }
            OrderItem existingItem = getItemById(id);
            if (existingItem == null) {
                throw new IllegalArgumentException("OrderItem with ID " + id + " not found");
            }
            // orderItem.setId(id);
            if (orderItem.getOrder() != null) {
                existingItem.setOrder(orderItem.getOrder());
            }
            if (orderItem.getProduct() != null) {
                existingItem.setProduct(orderItem.getProduct());
            }
            if (orderItem.getQuantity() > 0) {
                existingItem.setQuantity(orderItem.getQuantity());
            }
            if (orderItem.getTotal() > 0) {
                existingItem.setTotal(orderItem.getTotal());
            }

            // Save the updated OrderItem back to the repository
            return orderItemRespository.save(existingItem);

        } catch (Exception e) {
            throw new RuntimeException("Error while updating order items : " + e.getMessage(), e);

        }
    }

    public List<OrderItem> getItemsListById(List<Long> orderItemsId) {
        try {
            List<OrderItem> existingItems = orderItemRespository.findAllById(orderItemsId);
            if (existingItems.isEmpty()) {
                throw new OrderItemsNotFoundException("Order with IDs " + " not found.");
            }
            return existingItems;
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching list of order items : " + e.getMessage(), e);

        }
    }

    public OrderItem getItemById(Long id) {

        try {
            Optional<OrderItem> existingOrderItem = orderItemRespository.findById(id);
            if (existingOrderItem.isEmpty()) {
                throw new OrderItemsNotFoundException("Order items with ID >>> " + id + " not found.");
            }
            return existingOrderItem.get();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching   order items with id  : " + id + " Error >>>> " + e.getMessage(), e);

        }
    }

    public List<OrderItem> getItemsByOrderId(Long orderId) {
        try {
            final Optional<List<OrderItem>> response = orderItemRespository.findOrderItemsByOrderId(orderId);
            if (response.isEmpty()) {
                throw new OrderItemsNotFoundException("Order items not found with order id >>" + orderId);
            }
            return response.get();

        } catch (Exception e) {
            throw new RuntimeException("Error while fetching   order items with order id  : " + orderId + " Error >>>> " + e.getMessage(), e);

        }
    }

    public void deleteOrderItems(List<Long> itemsList) {
        try {
            final List<OrderItem> existingItems = getItemsListById(itemsList);
            if (existingItems.isEmpty()) {
                throw new OrderItemsNotFoundException("Order items with ID >>> " + " not found.");

            }
            orderItemRespository.deleteAllById(itemsList);

        } catch (Exception e) {
            throw new RuntimeException("Error while deleting order items >> " + e.getMessage());
        }
    }

}
