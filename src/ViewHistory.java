import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewHistory {
    public static void showHistory() {
        JFrame frame = new JFrame("Tax History");
        frame.setSize(500, 300);

        String[] columns = {"ID", "Name", "Income", "Tax"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tax_users")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double income = rs.getDouble("income");
                double tax = rs.getDouble("tax");
                model.addRow(new Object[]{id, name, income, tax});
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }

        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
}
