package TaskSecond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Dashboard extends JFrame implements ActionListener {

    JButton addEmployeeBtn, viewEmployeeBtn, updateEmployeeBtn, deleteEmployeeBtn, searchEmployeeBtn, logoutBtn;
    JButton changeThemeBtn;
    boolean isLightTheme = true;

    Color lightBackground = new Color(230, 230, 250);
    Color lightButtonColor = new Color(85, 183, 219);
    Color lightTextColor = Color.WHITE;
    Color lightHeadingColor = new Color(34, 34, 34);

    Color darkBackground = new Color(18, 18, 18);
    Color darkButtonColor = new Color(47, 133, 90);
    Color darkTextColor = Color.LIGHT_GRAY;
    Color darkHeadingColor = Color.WHITE;

    JPanel buttonPanel, titlePanel;
    JLabel titleLabel;

    public Dashboard() {
        setTitle("Employee Management System - Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titleLabel = new JLabel("Banking Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        add(buttonPanel, BorderLayout.CENTER);

        addEmployeeBtn = new JButton("Add Employee");
        viewEmployeeBtn = new JButton("View Employees");
        updateEmployeeBtn = new JButton("Update Employee");
        deleteEmployeeBtn = new JButton("Remove Employee");
        searchEmployeeBtn = new JButton("Search Employee");
        logoutBtn = new JButton("Logout");

        JButton[] buttons = {addEmployeeBtn, viewEmployeeBtn, updateEmployeeBtn, deleteEmployeeBtn, searchEmployeeBtn, logoutBtn};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setFocusPainted(false);
            buttonPanel.add(button);
        }

        changeThemeBtn = new JButton(isLightTheme ? "Dark" : "Light");
        changeThemeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        changeThemeBtn.setPreferredSize(new Dimension(80, 40));
        changeThemeBtn.setFocusPainted(false);
        changeThemeBtn.setBorderPainted(false);
        changeThemeBtn.setOpaque(true);

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(lightBackground);
        footerPanel.add(changeThemeBtn, BorderLayout.EAST);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(footerPanel, BorderLayout.SOUTH);

        addEmployeeBtn.addActionListener(this);
        viewEmployeeBtn.addActionListener(this);
        updateEmployeeBtn.addActionListener(this);
        deleteEmployeeBtn.addActionListener(this);
        searchEmployeeBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
        changeThemeBtn.addActionListener(this);

        applyTheme();
        setVisible(true);
    }

    private void applyTheme() {
        Color background, buttonColor, textColor, headingColor;

        if (isLightTheme) {
            background = lightBackground;
            buttonColor = lightButtonColor;
            textColor = lightTextColor;
            headingColor = lightHeadingColor;
        } else {
            background = darkBackground;
            buttonColor = darkButtonColor;
            textColor = darkTextColor;
            headingColor = darkHeadingColor;
        }

        titlePanel.setBackground(background);
        titleLabel.setForeground(headingColor);

        buttonPanel.setBackground(background);
        for (Component component : buttonPanel.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(buttonColor);
                component.setForeground(textColor);
            }
        }

        changeThemeBtn.setBackground(buttonColor);
        changeThemeBtn.setForeground(textColor);
        changeThemeBtn.setText(isLightTheme ? "Dark" : "Light");
        getContentPane().setBackground(background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEmployeeBtn) {
            new AddEmployeeWindow();
        } else if (e.getSource() == viewEmployeeBtn) {
            new ViewEmployeesWindow();
        } else if (e.getSource() == updateEmployeeBtn) {
            new UpdateEmployeeWindow();
        } else if (e.getSource() == deleteEmployeeBtn) {
            new DeleteEmployeeWindow();
        } else if (e.getSource() == searchEmployeeBtn) {
            new ResearchEmployeeWindow();
        } else if (e.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logging out...");
            new LoginScreen();
            dispose();
        } else if (e.getSource() == changeThemeBtn) {
            isLightTheme = !isLightTheme;
            applyTheme();
        }
    }
}
