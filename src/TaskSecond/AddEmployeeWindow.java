package TaskSecond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class AddEmployeeWindow extends JFrame implements ActionListener {
    JTextField idField, nameField, positionField, departmentField, salaryField, dojField;
    JComboBox<String> genderComboBox;
    JButton saveButton, cancelButton;

    public AddEmployeeWindow() {
        setTitle("Add Employee");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBackground(new Color(230, 230, 250));

        JLabel headingLabel = new JLabel("Add Employee", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(new Color(34, 34, 34));
        add(headingLabel, BorderLayout.NORTH);

        idField = new JTextField(20);
        nameField = new JTextField(20);
        positionField = new JTextField(20);
        departmentField = new JTextField(20);
        salaryField = new JTextField(20);
        dojField = new JTextField(20);
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        addFormComponents(formPanel);

        add(formPanel, BorderLayout.CENTER);
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        setVisible(true);
    }

    private void addFormComponents(JPanel formPanel) {
        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Position:"));
        formPanel.add(positionField);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentField);
        formPanel.add(new JLabel("Salary:"));
        formPanel.add(salaryField);
        formPanel.add(new JLabel("Date of Joining (YYYY-MM-DD):"));
        formPanel.add(dojField);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderComboBox);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            handleSave();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    private void handleSave() {
        String id = idField.getText();
        String name = nameField.getText();
        String position = positionField.getText();
        String department = departmentField.getText();
        String salary = salaryField.getText();
        String doj = dojField.getText();
        String gender = (String) genderComboBox.getSelectedItem();

        if (areInputsValid(id, name, position, department, salary, doj)) {
            saveEmployeeToDatabase(id, name, position, department, salary, doj, gender);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "All fields are required!");
        }
    }

    private boolean areInputsValid(String id, String name, String position, String department, String salary, String doj) {
        return !id.isEmpty() && !name.isEmpty() && !position.isEmpty() && !department.isEmpty() && !salary.isEmpty() && !doj.isEmpty();
    }

    private void saveEmployeeToDatabase(String id, String name, String position, String department, String salary, String doj, String gender) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "Mysql@2005")) {
            if (employeeExists(conn, id)) {
                JOptionPane.showMessageDialog(this, "Employee ID already exists. Please use a different ID.");
            } else {
                insertEmployee(conn, id, name, position, department, salary, doj, gender);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private boolean employeeExists(Connection conn, String id) throws SQLException {
        String checkQuery = "SELECT id FROM Employees WHERE id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setString(1, id);
            try (ResultSet rs = checkStmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private void insertEmployee(Connection conn, String id, String name, String position, String department, String salary, String doj, String gender) throws SQLException {
        String query = "INSERT INTO Employees (id, name, position, department, salary, date_of_joining, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, position);
            stmt.setString(4, department);
            stmt.setString(5, salary);
            stmt.setString(6, doj);
            stmt.setString(7, gender);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
            }
        }
    }


}
