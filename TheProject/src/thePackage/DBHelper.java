package thePackage;

import java.sql.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    // Kullanıcı tablosu oluştur
    public static void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                   + "username TEXT PRIMARY KEY,"
                   + "password TEXT NOT NULL)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Yeni kullanıcı ekle
    public static boolean insertUser(String username, String password) {
        if (getPasswordForUser(username) != null) return false; // zaten varsa ekleme
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kullanıcının şifresini getir
    public static String getPasswordForUser(String username) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}