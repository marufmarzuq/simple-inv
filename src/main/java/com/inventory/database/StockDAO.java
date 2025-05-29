package com.inventory.database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StockDAO {

    public static void stockInOut(int productId, int qty, String action) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);

        try {
            // Insert into stock_logs
            String logSql = "INSERT INTO stock_logs (product_id, quantity, action, date_time) VALUES (?, ?, ?, NOW())";
            try (PreparedStatement logStmt = conn.prepareStatement(logSql)) {
                logStmt.setInt(1, productId);
                logStmt.setInt(2, qty);
                logStmt.setString(3, action);
                logStmt.executeUpdate();
            }

            // Update stock table
            String checkSql = "SELECT * FROM stock WHERE product_id=?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, productId);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    int current = rs.getInt("quantity");
                    int newQty = action.equals("in") ? current + qty : current - qty;
                    if (newQty < 0) newQty = 0;
                    String updateSql = "UPDATE stock SET quantity=? WHERE product_id=?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, newQty);
                        updateStmt.setInt(2, productId);
                        updateStmt.executeUpdate();
                    }
                } else {
                    String insertSql = "INSERT INTO stock (product_id, quantity) VALUES (?, ?)";
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

    public static Map<Integer, Integer> getAllStock() throws Exception {
        Map<Integer, Integer> stockMap = new HashMap<>();
        String sql = "SELECT * FROM stock";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                stockMap.put(rs.getInt("product_id"), rs.getInt("quantity"));
            }
        }
        return stockMap;
    }
}
