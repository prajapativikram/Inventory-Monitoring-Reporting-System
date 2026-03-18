package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String category;
    private int quantity;
    private double price;
    private int reorderLevel;
    private LocalDateTime lastUpdated;



    @PrePersist
    @PreUpdate
    public void updateTime() {
        lastUpdated = LocalDateTime.now();
    }

    public InventoryItem() {}

    private int threshold = 5;

    public boolean isLowStock(){
        return quantity <= threshold;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(int reorderLevel) { this.reorderLevel = reorderLevel; }
}