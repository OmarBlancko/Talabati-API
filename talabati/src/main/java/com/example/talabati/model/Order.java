package com.example.talabati.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    @ManyToMany
    @JoinTable(
        name = "order_product",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> orderItems;
    @Column(nullable = false)
    private String deliveryAdress;
    @Column(nullable = false)
    private double totalPrice;
    @Column(nullable = false)
    private String status;
    private int deliveryId;
    // private Payment payment;
    @Column( nullable = false)
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();

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

    public List<Product> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<Product> orderItems) {
        this.orderItems = orderItems;
    }

    public String getDeliveryAdress() {
        return this.deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
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

    // public Payment getPayment() {
    //     return this.payment;
    // }

    // public void setPayment(Payment payment) {
    //     this.payment = payment;
    // }

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

}
