package com.example.talabati.service;

import org.springframework.stereotype.Service;

import com.example.talabati.repositories.OrderItemRespository;

@Service
public class OrderItemsService {
    private OrderItemRespository orderItemRespository;


    public OrderItemsService() {
    }

    public OrderItemsService(OrderItemRespository orderItemRespository) {
        this.orderItemRespository = orderItemRespository;
    }
    
}
