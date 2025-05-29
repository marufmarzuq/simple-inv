package com.inventory.database;

import com.inventory.model.StockLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockLogDAO {
    public static List<StockLog> getLogs(String fromDate, String toDate) throws Exception {
        List<StockLog> logs = new ArrayList<>();
        String sql = "SELECT product_id, p.name AS product_name, p.category AS product_category, action_type, quantity, stock_log.created_at AS created_at FROM stock_log LEFT JOIN products p ON stock_log.product_id = p.id";

        if(fromDate != null && toDate != null) sql += "  WHERE stock_log.created_at BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if(fromDate != null && toDate != null) {
                stmt.setString(1, fromDate + " 00:00:00");
                stmt.setString(2, toDate + " 23:59:59");
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                logs.add(new StockLog(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_category"),
                        rs.getString("action_type"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        }
        return logs;
    }
}
