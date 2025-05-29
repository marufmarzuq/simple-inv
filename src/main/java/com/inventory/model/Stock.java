package com.inventory.model;

public class Stock {
    private int productId;
    private String productName;
    private String productCategory;
    private Double unitPrice;
    private int currentStock;

    public Stock(int productId, String productName, String productCategory, Double unitPrice, int currentStock) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.unitPrice = unitPrice;
        this.currentStock = currentStock;
    }

    // Getters and setters
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getProductCategory() { return productCategory; }
    public Double getUnitPrice() { return unitPrice; }
    public int getCurrentStock() { return currentStock; }

    public void setProductId(int productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    public void setCurrentStock(int currentStock) { this.currentStock = currentStock; }

    public String toString() {
        return productName;
    }
}
