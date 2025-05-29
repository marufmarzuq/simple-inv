package com.inventory.model;

import java.time.LocalDateTime;

public class StockLog {
    private int id;
    private int productId;
    private int quantity;
    private String action; // "in" or "out"
    private LocalDateTime dateTime;

    public StockLog(int id, int productId, int quantity, String action, LocalDateTime dateTime) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.action = action;
        this.dateTime = dateTime;
    }

    // Getters and setters
}
