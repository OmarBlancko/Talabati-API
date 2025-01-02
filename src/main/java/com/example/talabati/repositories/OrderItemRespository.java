package com.example.talabati.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.talabati.model.OrderItem;

@Repository
public interface OrderItemRespository extends JpaRepository<OrderItem, Long> {

    Optional<List<OrderItem>> findOrderItemsByOrderId(Long orderId);

}
