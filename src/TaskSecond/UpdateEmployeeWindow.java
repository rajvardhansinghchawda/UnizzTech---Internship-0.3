package TaskSecond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateEmployeeWindow extends JFrame implements ActionListener {
    JTextField idField, nameField, positionField, departmentField, salaryField, dojField;
    JComboBox<String> genderComboBox;
    JButton searchButton, updateButton, cancelButton;

    public UpdateEmployeeWindow() {
        setTitle("Update Employee");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel headingLabel = new JLabel("Update Employee", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(new Color(34, 34, 34));
        add(headingLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(230, 230, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        idField = new JTextField(20);
        nameField = new JTextField(20);
        positionField = new JTextField(20);
        departmentField = new JTextField(20);
        salaryField = new JTextField(20);
        dojField = new JTextField(20);
        genderComboBox = new JComboBox<>(new String[] {"Male", "Female"});

        nameField.setEditable(false);
        positionField.setEditable(false);
        departmentField.setEditable(false);
        salaryField.setEditable(false);
        dojField.setEditable(false);
        genderComboBox.setEnabled(false);

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1; formPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Position:"), gbc);
        gbc.gridx = 1; formPanel.add(positionField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; formPanel.add(departmentField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1; formPanel.add(salaryField, gbc);

        gbc.gridx = 0; gbc.gridy = 5; formPanel.add(new JLabel("Date of Joining:"), gbc);
        gbc.gridx = 1; formPanel.add(dojField, gbc);

        gbc.gridx = 0; gbc.gridy = 6; formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1; formPanel.add(genderComboBox, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 230, 250));

        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        cancelButton = new JButton("Cancel");

        searchButton.setBackground(new Color(85, 183, 219));
        updateButton.setBackground(new Color(85, 183, 219));
        cancelButton.setBackground(new Color(255, 99, 71));

        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        searchButton.addActionListener(this);
        updateButton.addActionListener(this);
        cancelButton.addActionListener(this);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchEmployeeById(idField.getText());
        } else if (e.getSource() == updateButton) {
            String id = idField.getText();
            String name = nameField.getText();
            String position = positionField.getText();
            String department = departmentField.getText();
            String salary = salaryField.getText();
            String doj = dojField.getText();
            String gender = (String) genderComboBox.getSelectedItem();

            if (name.isEmpty() || position.isEmpty() || department.isEmpty() || salary.isEmpty() || doj.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            } else {
                updateEmployeeDetails(id, name, position, department, salary, doj, gender);
                dispose();
            }
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    private void searchEmployeeById(String id) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "Mysql@2005")) {
            String query = "SELECT * FROM Employees WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    positionField.setText(rs.getString("position"));
                    departmentField.setText(rs.getString("department"));
                    salaryField.setText(String.valueOf(rs.getDouble("salary")));
                    dojField.setText(rs.getString("date_of_joining"));
                    genderComboBox.setSelectedItem(rs.getString("gender"));

                    enableFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found!");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void enableFields() {
        nameField.setEditable(true);
        positionField.setEditable(true);
        departmentField.setEditable(true);
        salaryField.setEditable(true);
        dojField.setEditable(true);
        genderComboBox.setEnabled(true);
    }

    private void updateEmployeeDetails(String id, String name, String position, String department, String salary, String doj, String gender) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "Mysql@2005")) {
            String query = "UPDATE Employees SET name = ?, position = ?, department = ?, salary = ?, date_of_joining = ?, gender = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, position);
                stmt.setString(3, department);
                stmt.setString(4, salary);
                stmt.setString(5, doj);
                stmt.setString(6, gender);
                stmt.setString(7, id);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
