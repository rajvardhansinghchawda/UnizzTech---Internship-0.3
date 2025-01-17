package TaskSecond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeManagementSystem {

    public static void main(String[] args) {
        // Start the application by showing the welcome screen
        SwingUtilities.invokeLater(WelcomeScreen::new);
    }
}

class WelcomeScreen extends JFrame {

    public WelcomeScreen() {
        // Set up the basic properties of the window
        setTitle("Welcome to Employee Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a background panel with a nice gradient
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Set up a gradient from top to bottom
                Color startColor = new Color(85, 183, 219);
                Color endColor = new Color(38, 70, 83);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel);

        // Create welcome labels
        JLabel welcomeLabel = new JLabel("Welcome to", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 35));
        welcomeLabel.setForeground(Color.WHITE);

        JLabel systemNameLabel = new JLabel("Employee Management System", SwingConstants.CENTER);
        systemNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // Group the labels in a transparent panel
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(welcomeLabel);
        textPanel.add(systemNameLabel);

        // Create a footer label for loading message
        JLabel footerLabel = new JLabel("Loading, please wait...", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Add components to the background panel
        backgroundPanel.add(textPanel, BorderLayout.CENTER);
        backgroundPanel.add(footerLabel, BorderLayout.SOUTH);

        // Make the window visible
        setVisible(true);

        // After 4 seconds, move to the login screen
        Timer timer = new Timer(4000, e -> {
            new LoginScreen();
            dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }
}

class LoginScreen extends JFrame implements ActionListener {
    // Declare components for the login screen
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;

    public LoginScreen() {
        // Set up the frame properties
        setTitle("Login - Employee Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Create and add components for username input
        JPanel usernamePanel = new JPanel(new FlowLayout());
        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(20);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        // Create and add components for password input
        JPanel passwordPanel = new JPanel(new FlowLayout());
        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Create and add buttons for login and back actions
        JPanel buttonPanel = new JPanel(new FlowLayout());
        loginButton = new JButton("Login");
        backButton = new JButton("Back");
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        // Add panels to the frame
        add(usernamePanel);
        add(passwordPanel);
        add(buttonPanel);

        // Attach action listeners to buttons
        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        // Show the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } else if (e.getSource() == backButton) {
            JOptionPane.showMessageDialog(this, "Going Back!");
            System.exit(0);
        }
    }

    // This method checks if the provided username and password are correct
    private boolean authenticate(String username, String password) {
        boolean isValid = false;
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EmployeeManagement",
                    "root",
                    "Mysql@2005"
            );

            // Prepare and execute a query to check the username and password
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }

            // Close the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Connection Error!");
        }
        return isValid;
    }
}

// Note: The `Dashboard` class needs to be defined elsewhere.
