package com.example.talabati.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id") // Foreign key to Order table
    private Order order;    
    @ManyToOne
    @JoinColumn(name = "product_id") // Maps a foreign key to Product
    private Product product;
    @Column(nullable=false)
    private int quantity;
    @Column(nullable=false)
    private double total;   

    public OrderItem() {
    }

    public OrderItem(long id, Order  order, Product product, int quantity, double total) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product product() {
        return this.product;
    }

    public void setProductId(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    

}
