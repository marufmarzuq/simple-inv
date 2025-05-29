package com.inventory.model;

public class Product {
    private int id;
    private String name;
    private String category;
    private String description;
    private double unitPrice;

    public Product(int id, String name, String category, String description, double unitPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    public Product(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Product(String name, String category, String description, double unitPrice) {
        this(-1, name, category, description, unitPrice);
    }

    // Getters and setters
    public int getId() { return id; }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getUnitPrice() { return unitPrice; }

    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}
