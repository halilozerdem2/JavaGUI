package thePackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public static class User {
        public String username;
        public String password;
        public int age;
        public String gender;
        public String address;
        public String department;

        public User(String username, String password, int age, String gender, String address, String department) {
            this.username = username;
            this.password = password;
            this.age = age;
            this.gender = gender;
            this.address = address;
            this.department = department;
        }
    }

    public static void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                   + "username TEXT PRIMARY KEY, "
                   + "password TEXT NOT NULL, "
                   + "age INTEGER, "
                   + "gender TEXT, "
                   + "address TEXT, "
                   + "department TEXT)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Failed to create table: " + e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static boolean insertUser(String username, String password) {
        if (getPasswordForUser(username) != null) return false;
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to insert user: " + e.getMessage());
            return false;
        }
    }

    public static void updateUserDetails(String username, int age, String gender, String address, String department) {
        String sql = "UPDATE users SET age = ?, gender = ?, address = ?, department = ? WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, age);
            pstmt.setString(2, gender);
            pstmt.setString(3, address);
            pstmt.setString(4, department);
            pstmt.setString(5, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update user details: " + e.getMessage());
        }
    }

    public static String getPasswordForUser(String username) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve password: " + e.getMessage());
        }
        return null;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT username, password, age, gender, address, department FROM users";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("address"),
                    rs.getString("department")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch users: " + e.getMessage());
        }
        return users;
    }
}
