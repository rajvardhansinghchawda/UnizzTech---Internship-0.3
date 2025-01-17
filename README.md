# Employee Management System

## Overview

The **Employee Management System** is a desktop-based application developed using **Java Swing** and **MySQL**. It provides an intuitive interface to manage employee records, including adding, updating, deleting, and researching employee data. The project is structured with clear separation of screens like Welcome, Login, and Dashboard.

---

## Features

1. **Welcome Screen**:

    - Displays a gradient background with a welcome message.
    - Automatically transitions to the login screen after 4 seconds.

2. **Login Screen**:

    - Allows admins to log in using their credentials.
    - Authenticates the user against the `Users` table in the database.
    - Displays an error message for invalid credentials.

3. **Dashboard**:

    - Serves as the main navigation hub for all employee management operations.
    - Includes buttons to access the following functionalities:
        - Add Employee
        - Update Employee
        - Delete Employee
        - Research Employee
        - View All Employees

4. **Add Employee**:

    - Allows admins to manually input employee details, including:
        - **Employee ID** (must be unique).
        - Name, Position, Department, Salary, Date of Joining, and Gender.
    - Validates inputs and ensures all fields are filled.
    - Prevents duplicate Employee IDs.

5. **Update Employee**:

    - Enables searching for an employee by **Employee ID**.
    - Displays the current details of the employee, allowing the admin to update them.
    - Ensures the updated data is saved back to the database.

6. **Delete Employee**:

    - Allows searching for an employee by **Employee ID**.
    - Displays the employee's details before confirming deletion.
    - Deletes the employee record upon confirmation.

7. **Research Employee**:

    - Allows searching for employees based on:
        - Employee ID
        - Name
        - Position
        - Department
    - Displays the search results in a table format.

8. **Database Integration**:

    - Uses **MySQL** to store and manage employee and user records.
    - Provides secure authentication and data validation.

---

## Technologies Used

- **Programming Language**: Java (Swing for GUI)
- **Database**: MySQL
- **Tools**: IntelliJ IDEA / Eclipse (for development), MySQL Workbench (for database management)

---

## Database Schema

### Table: `Users`

| Column Name | Data Type   | Constraints |
| ----------- | ----------- | ----------- |
| username    | VARCHAR(50) | PRIMARY KEY |
| password    | VARCHAR(50) | NOT NULL    |

### Table: `Employees`

| Column Name       | Data Type     | Constraints |
| ----------------- | ------------- | ----------- |
| id                | VARCHAR(50)   | PRIMARY KEY |
| name              | VARCHAR(100)  | NOT NULL    |
| position          | VARCHAR(100)  | NOT NULL    |
| department        | VARCHAR(100)  | NOT NULL    |
| salary            | DECIMAL(10,2) | NOT NULL    |
| date\_of\_joining | DATE          | NOT NULL    |
| gender            | VARCHAR(10)   | NOT NULL    |

---

## How to Run

1. **Prerequisites**:

    - Install Java JDK 8 or later.
    - Install MySQL and create the required database and tables.
    - Install an IDE like IntelliJ IDEA or Eclipse.

2. **Setup Database**:

    - Use the following SQL commands to create the database and tables:
      ```sql
      CREATE DATABASE EmployeeManagement;
 
      CREATE TABLE Users (
          username VARCHAR(50) PRIMARY KEY,
          password VARCHAR(50) NOT NULL
      );
 
      CREATE TABLE Employees (
          id VARCHAR(50) PRIMARY KEY,
          name VARCHAR(100) NOT NULL,
          position VARCHAR(100) NOT NULL,
          department VARCHAR(100) NOT NULL,
          salary DECIMAL(10,2) NOT NULL,
          date_of_joining DATE NOT NULL,
          gender VARCHAR(10) NOT NULL
      );
      ```
    - Insert a test admin user into the `Users` table:
      ```sql
      INSERT INTO Users (username, password) VALUES ('admin', 'admin123');
      ```

3. **Run the Project**:

    - Clone or download the project files.
    - Open the project in your IDE.
    - Update the database connection credentials in the code (e.g., `root` and `password`).
    - Run the `EmployeeManagementSystem` class.

---

## Code Structure

```
TaskSecond
├── EmployeeManagementSystem.java   // Main entry point
├── WelcomeScreen.java             // Welcome screen with gradient background
├── LoginScreen.java               // Login screen for admin authentication
├── Dashboard.java                 // Dashboard with navigation buttons
├── AddEmployeeWindow.java         // Add Employee functionality
├── UpdateEmployeeWindow.java      // Update Employee functionality
├── DeleteEmployeeWindow.java      // Delete Employee functionality
├── ResearchEmployeeWindow.java    // Research Employee functionality
└── ViewEmployeesWindow.java       // View all employees in a table
```

---

##

---

# Currency Converter Application

## Overview

The Currency Converter is a Java application that allows users to convert amounts between four predefined currencies:

- Rupee (INR)
- Euro (EUR)
- British Pound (GBP)
- US Dollar (USD)

This project consists of two classes:

1. **`Main`**: Handles user interaction and program flow.
2. **`convertor`**: Implements the conversion logic.

## Features

- Interactive console-based user interface.
- Support for four currencies with hardcoded exchange rates.
- Option to perform multiple conversions in a single session.

## How It Works

1. The user is presented with a menu of currencies to choose from.
2. The user selects the source (`from`) and target (`to`) currencies by entering the corresponding menu numbers.
3. The user enters the amount to be converted.
4. The application calculates and displays the converted amount.
5. The user can choose to perform additional conversions or exit the application.

## Example Usage

### Input:

````Welcome
1. Rupee    2. Euro (EUR)    3. British Pound (GBP)    4. US Dollar (USD)
From Currency: 1 (Rupee)
To Currency: 4 (USD)
Amount: 500```

### Output:
```Rupee to USD is 6.0.```

## Internship Context
This application was developed as part of the tasks assigned during the **Java Developer Internship (JD-INT004)** at UnizzTech (21/12/2024 to 20/01/2025). It was completed during the first week of the internship and served as a practical exercise to enhance Java programming skills.

## File Structure
````

project-directory/
│
├── src/
│   ├── firstTask/
│       ├── Main.java
│       ├── convertor.java
│
└── README.md

```

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- An IDE or text editor for Java development (e.g., IntelliJ IDEA, Eclipse, VS Code)

---

## Contributors

- **RAJWARDHAN SINGH CHAWDA**

Feel free to reach out for any queries or suggestions!

```
