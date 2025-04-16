import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASSWORD = "manager"; // Change this if needed

    // Establish connection
    public static Connection connect() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Save tax record
    public static void saveTaxRecord(String name, double income, double tax) throws Exception {
        String query = "INSERT INTO tax_users (name, income, tax) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setDouble(2, income);
            ps.setDouble(3, tax);
            ps.executeUpdate();
        }
    }

    // Register a new user
    public static boolean registerUser(String username, String password) throws Exception {
        // Check if user already exists
        String checkQuery = "SELECT * FROM users WHERE username = ?";
        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return false; // User already exists
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();
                return true;
            }
        }
    }

    // Validate login credentials
    public static boolean validateUser(String username, String password) throws Exception {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
}
