package com.inventory.database;

import com.inventory.model.StockLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockLogDAO {
    public static List<StockLog> getLogs(String fromDate, String toDate) throws Exception {
        List<StockLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM stock_logs WHERE date_time BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fromDate + " 00:00:00");
            stmt.setString(2, toDate + " 23:59:59");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                logs.add(new StockLog(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getString("action"),
                        rs.getTimestamp("date_time").toLocalDateTime()
                ));
            }
        }
        return logs;
    }
}
