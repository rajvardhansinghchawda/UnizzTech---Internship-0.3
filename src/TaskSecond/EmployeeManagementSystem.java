package TaskSecond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeManagementSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeScreen::new);
    }
}

class WelcomeScreen extends JFrame {

    public WelcomeScreen() {
        setTitle("Welcome to Employee Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(85, 183, 219);
                Color endColor = new Color(38, 70, 83);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel);

        JLabel welcomeLabel = new JLabel("Welcome to", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 35));
        welcomeLabel.setForeground(Color.WHITE);

        JLabel systemNameLabel = new JLabel("Employee Management System", SwingConstants.CENTER);
        systemNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(welcomeLabel);
        textPanel.add(systemNameLabel);

        JLabel footerLabel = new JLabel("Loading, please wait...", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        backgroundPanel.add(textPanel, BorderLayout.CENTER);
        backgroundPanel.add(footerLabel, BorderLayout.SOUTH);

        setVisible(true);

        Timer timer = new Timer(4000, e -> {
            new LoginScreen();
            dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }
}

class LoginScreen extends JFrame implements ActionListener {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton backButton;

    public LoginScreen() {
        setTitle("Login - Employee Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        JPanel usernamePanel = createInputPanel("Username: ", usernameField);
        JPanel passwordPanel = createInputPanel("Password: ", passwordField);
        JPanel buttonPanel = createButtonPanel();

        add(usernamePanel);
        add(passwordPanel);
        add(buttonPanel);

        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        setVisible(true);
    }

    private JPanel createInputPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel(labelText));
        panel.add(textField);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(loginButton);
        panel.add(backButton);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (authenticate(usernameField.getText(), new String(passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } else if (e.getSource() == backButton) {
            System.exit(0);
        }
    }

    private boolean authenticate(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "Mysql@2005");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Connection Error!");
            return false;
        }
    }
}
