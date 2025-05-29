package com.inventory.model;

import java.time.LocalDateTime;

public class StockLog {
    private int productId;
    private String productName;
    private String productCategory;
    private String actionType; // "In" or "Out"
    private int quantity;
    private LocalDateTime date;

    public StockLog(int productId, String productName, String productCategory, String actionType, int quantity, LocalDateTime date) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.actionType = actionType;
        this.quantity = quantity;
        this.date = date;
    }

    // Getters and setters
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getProductCategory() { return productCategory; }
    public String getActionType() { return actionType; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getDate() { return date; }

    public void setProductId(int productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
