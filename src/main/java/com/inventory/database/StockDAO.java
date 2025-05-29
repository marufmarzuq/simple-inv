package com.inventory.database;

import com.inventory.model.Product;
import com.inventory.model.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockDAO {

    public static void stockInOut(String action, int productId, int qty) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);

        try {
            // Insert into stock_log
            String logSql = "INSERT INTO stock_log (product_id, action_type, quantity, created_at) VALUES (?, ?, ?, NOW())";
            try (PreparedStatement logStmt = conn.prepareStatement(logSql)) {
                logStmt.setInt(1, productId);
                logStmt.setString(2, action);
                    logStmt.setInt(3, qty);
                logStmt.executeUpdate();
            }

            // Update stock table
            String checkSql = "SELECT * FROM stock WHERE product_id=?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, productId);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    int current = rs.getInt("current_stock");
                    int newQty = action.equals("In") ? current + qty : current - qty;
                    if (newQty < 0) newQty = 0;
                    String updateSql = "UPDATE stock SET current_stock=? WHERE product_id=?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, newQty);
                        updateStmt.setInt(2, productId);
                        updateStmt.executeUpdate();
                    }
                } else {
                    String insertSql = "INSERT INTO stock (product_id, current_stock) VALUES (?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, productId);
                        insertStmt.setInt(2, qty);
                        insertStmt.executeUpdate();
                    }
                }
            }

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public static List<Stock> getProductsWithStocks() throws Exception {
        List<Stock> list = new ArrayList<>();
        String sql = "SELECT id, name, category, unit_price, stock.current_stock as current_stock FROM products LEFT JOIN stock ON products.id = stock.product_id";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Stock p = new Stock(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("unit_price"),
                        rs.getInt("current_stock")
                );
                list.add(p);
            }
        }
        System.out.println();
        return list;
    }
}
