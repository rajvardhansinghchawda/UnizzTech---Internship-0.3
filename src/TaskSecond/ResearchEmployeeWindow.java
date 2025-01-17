package TaskSecond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

class ResearchEmployeeWindow extends JFrame implements ActionListener {
    JTextField searchField;
    JComboBox<String> searchCriteriaComboBox;
    JButton searchButton, closeButton;
    JTable resultTable;
    DefaultTableModel tableModel;

    public ResearchEmployeeWindow() {
        setTitle("Research Employee");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel headingLabel = new JLabel("Research Employee", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(new Color(34, 34, 34));
        add(headingLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(new Color(230, 230, 250));

        JLabel searchLabel = new JLabel("Search by:");
        searchField = new JTextField(20);
        searchCriteriaComboBox = new JComboBox<>(new String[]{"Employee ID", "Name", "Position", "Department"});
        searchButton = new JButton("Search");

        searchPanel.add(searchLabel);
        searchPanel.add(searchCriteriaComboBox);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Position", "Department", "Salary", "Date of Joining", "Gender"}, 0);
        resultTable = new JTable(tableModel);
        resultTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 230, 250));

        closeButton = new JButton("Close");
        closeButton.setBackground(new Color(255, 99, 71));
        closeButton.setForeground(Color.WHITE);

        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(this);
        closeButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchTerm = searchField.getText();
            String searchCriteria = (String) searchCriteriaComboBox.getSelectedItem();
            searchEmployee(searchTerm, searchCriteria);
        } else if (e.getSource() == closeButton) {
            dispose();
        }
    }

    private void searchEmployee(String searchTerm, String searchCriteria) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "Mysql@2005")) {
            String query = "";
            switch (searchCriteria) {
                case "Employee ID": query = "SELECT * FROM Employees WHERE id LIKE ?"; break;
                case "Name": query = "SELECT * FROM Employees WHERE name LIKE ?"; break;
                case "Position": query = "SELECT * FROM Employees WHERE position LIKE ?"; break;
                case "Department": query = "SELECT * FROM Employees WHERE department LIKE ?"; break;
            }

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + searchTerm + "%");

            ResultSet rs = stmt.executeQuery();
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

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
