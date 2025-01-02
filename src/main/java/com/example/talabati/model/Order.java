package com.example.talabati.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int userId;
    @ElementCollection
    // @CollectionTable(name = "order_item_ids", joinColumns = @JoinColumn(name = "order_id", nullable = false))
    // @Column(nullable = false, name = "order_item_id")
    // private List<Long> orderItemsIds;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
    @Column(nullable = false)
    private String deliveryAddress;
    @Column(nullable = false)
    private double totalPrice;
    @Column(nullable = false)
    private String status;
    @OneToOne(cascade = CascadeType.ALL)   
    @JoinColumn(name = "payment_id", referencedColumnName = "id",nullable=false) // Proper mapping for OneToOne
    private Payment payment;
    private int deliveryId;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Order(Long id, int userId,List<OrderItem> orderItems, String deliveryAdress, double totalPrice, String status,  int deliveryId, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
        this.deliveryAddress = deliveryAdress;
        this.totalPrice = totalPrice;
        this.status = status;
        this.deliveryId = deliveryId;

        if (createdAt == null) {
            this.createdAt = LocalDateTime.now();
        } else {
            this.createdAt = createdAt;
        }
    
        if (updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        } else {
            this.updatedAt = updatedAt;
        }
    }

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = createdAt;  // Ensure updatedAt is set if not provided
        }

    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Order() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItem>  getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAdress) {
        this.deliveryAddress = deliveryAdress;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDeliveryId() {
        return this.deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime lastUpdateAt) {
        this.updatedAt = lastUpdateAt;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


}
