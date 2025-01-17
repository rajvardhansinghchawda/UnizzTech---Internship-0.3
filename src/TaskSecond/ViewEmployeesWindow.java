package TaskSecond;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

class ViewEmployeesWindow extends JFrame {

    JTable employeesTable;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JButton closeButton;

    public ViewEmployeesWindow() {
        setTitle("View Employees");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel headingLabel = new JLabel("Employees List", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 35));
        headingLabel.setForeground(new Color(34, 34, 34));
        add(headingLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Position", "Department", "Salary", "Date of Joining", "Gender"}, 0);
        employeesTable = new JTable(tableModel);
        employeesTable.setFillsViewportHeight(true);
        employeesTable.setFont(new Font("Arial", Font.PLAIN, 16));
        employeesTable.setRowHeight(30);

        scrollPane = new JScrollPane(employeesTable);
        add(scrollPane, BorderLayout.CENTER);

        closeButton = new JButton("Close");
        closeButton.setBackground(new Color(85, 183, 219));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 25));
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(150, 60));

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(230, 230, 250));
        footerPanel.add(closeButton);
        add(footerPanel, BorderLayout.SOUTH);

        closeButton.addActionListener(e -> dispose());

        loadEmployees();
        setVisible(true);
    }

    private void loadEmployees() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "Mysql@2005");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Employees")) {

            tableModel.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getString("department"),
                        rs.getDouble("salary"),
                        rs.getDate("date_of_joining"),
                        rs.getString("gender")
                };
                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
