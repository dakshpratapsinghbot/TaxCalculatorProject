import javax.swing.*;

public class LoginPage {
    public static void showLogin() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            // Simple static check
            if (username.equals("admin") && password.equals("admin")) {
                loginFrame.dispose();
                Main.showMainUI(); // Launch Main UI
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid credentials!");
            }
        });

        panel.add(userLabel); panel.add(userField);
        panel.add(passLabel); panel.add(passField);
        panel.add(loginButton);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }
}

//       javac -cp ".;../lib/ojdbc8.jar" *.java
//       java -cp ".;../lib/ojdbc8.jar" MainApp