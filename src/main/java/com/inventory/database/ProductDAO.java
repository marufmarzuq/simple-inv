package com.inventory.database;

import com.inventory.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static void addProduct(Product product) throws Exception {
        String sql = "INSERT INTO products (name, category, description, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getUnitPrice());
            stmt.executeUpdate();
        }
    }

    public static void updateProduct(Product product) throws Exception {
        String sql = "UPDATE products SET name=?, category=?, description=?, unit_price=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getUnitPrice());
            stmt.setInt(5, product.getId());
            stmt.executeUpdate();
        }
    }

    public static void deleteProduct(int id) throws Exception {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<Product> getAllProducts() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getDouble("unit_price")
                );
                list.add(p);
            }
        }
        System.out.println();
        return list;
    }
}
