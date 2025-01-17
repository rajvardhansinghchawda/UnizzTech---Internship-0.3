package TaskSecond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteEmployeeWindow extends JFrame implements ActionListener {
    JTextField idField, nameField, positionField, departmentField, salaryField, dojField;
    JComboBox<String> genderComboBox;
    JButton searchButton, deleteButton, cancelButton;

    public DeleteEmployeeWindow() {
        setTitle("Delete Employee");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel headingLabel = new JLabel("Delete Employee", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(new Color(34, 34, 34));
        add(headingLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(230, 230, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel idLabel = new JLabel("Employee ID:");
        idField = new JTextField(20);
        idField.setEditable(true);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setEditable(false);

        JLabel positionLabel = new JLabel("Position:");
        positionField = new JTextField(20);
        positionField.setEditable(false);

        JLabel departmentLabel = new JLabel("Department:");
        departmentField = new JTextField(20);
        departmentField.setEditable(false);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryField = new JTextField(20);
        salaryField.setEditable(false);

        JLabel dojLabel = new JLabel("Date of Joining (YYYY-MM-DD):");
        dojField = new JTextField(20);
        dojField.setEditable(false);

        JLabel genderLabel = new JLabel("Gender:");
        genderComboBox = new JComboBox<>(new String[] {"Male", "Female"});
        genderComboBox.setEnabled(false);

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(idLabel, gbc);
        gbc.gridx = 1; formPanel.add(idField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(nameLabel, gbc);
        gbc.gridx = 1; formPanel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(positionLabel, gbc);
        gbc.gridx = 1; formPanel.add(positionField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(departmentLabel, gbc);
        gbc.gridx = 1; formPanel.add(departmentField, gbc);
        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(salaryLabel, gbc);
        gbc.gridx = 1; formPanel.add(salaryField, gbc);
        gbc.gridx = 0; gbc.gridy = 5; formPanel.add(dojLabel, gbc);
        gbc.gridx = 1; formPanel.add(dojField, gbc);
        gbc.gridx = 0; gbc.gridy = 6; formPanel.add(genderLabel, gbc);
        gbc.gridx = 1; formPanel.add(genderComboBox, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 230, 250));

        searchButton = new JButton("Search");
        deleteButton = new JButton("Delete");
        cancelButton = new JButton("Cancel");

        searchButton.setBackground(new Color(85, 183, 219));
        searchButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(255, 99, 71));
        deleteButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(200, 200, 200));
        cancelButton.setForeground(Color.BLACK);

        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);

        searchButton.addActionListener(this);
        deleteButton.addActionListener(this);
        cancelButton.addActionListener(this);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String id = idField.getText();
            searchEmployeeById(id);
        } else if (e.getSource() == deleteButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String id = idField.getText();
                deleteEmployee(id);
                dispose();
            }
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    private void searchEmployeeById(String id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "Mysql@2005");

            String query = "SELECT * FROM Employees WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                positionField.setText(rs.getString("position"));
                departmentField.setText(rs.getString("department"));
                salaryField.setText(String.valueOf(rs.getDouble("salary")));
                dojField.setText(rs.getString("date_of_joining"));
                genderComboBox.setSelectedItem(rs.getString("gender"));

                nameField.setEditable(false);
                positionField.setEditable(false);
                departmentField.setEditable(false);
                salaryField.setEditable(false);
                dojField.setEditable(false);
                genderComboBox.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found!");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteEmployee(String id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "Mysql@2005");

            String query = "DELETE FROM Employees WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
            }

            stmt.close();
            conn.close();
            dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
