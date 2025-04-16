import javax.swing.*;
import java.awt.*;

public class Main {
    public static void showMainUI() {
        JFrame frame = new JFrame("Tax Calculator");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel incomeLabel = new JLabel("Income:");
        JTextField incomeField = new JTextField();

        JLabel taxLabel = new JLabel("Tax:");
        JTextField taxField = new JTextField();
        taxField.setEditable(false);

        JButton calcSaveButton = new JButton("Calculate & Save");
        calcSaveButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                double income = Double.parseDouble(incomeField.getText());

                double tax;
                if (income <= 250000) tax = 0;
                else if (income <= 500000) tax = (income - 250000) * 0.05;
                else if (income <= 1000000) tax = 12500 + (income - 500000) * 0.2;
                else tax = 112500 + (income - 1000000) * 0.3;

                taxField.setText(String.format("%.2f", tax));

                DBConnection.saveTaxRecord(name, income, tax);
                JOptionPane.showMessageDialog(frame, "Tax calculated and saved!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter valid income.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        JButton historyButton = new JButton("View History");
        historyButton.addActionListener(e -> ViewHistory.showHistory());

        panel.add(nameLabel); panel.add(nameField);
        panel.add(incomeLabel); panel.add(incomeField);
        panel.add(taxLabel); panel.add(taxField);
        panel.add(calcSaveButton); panel.add(historyButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
