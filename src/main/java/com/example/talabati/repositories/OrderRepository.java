package com.example.talabati.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.talabati.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    Optional<List<Order>> findOrdersByUserId(Long userId);

}
