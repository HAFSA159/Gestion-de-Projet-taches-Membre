package com.example.taskmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    private static final String URL = "jdbc:mysql://localhost:3306/taskmanager";  // Database name is now 'taskmanager'
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789.";

    static {
        // Optional: Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "MySQL Driver not found", e);
        }
    }

    // Establish connection with the 'taskmanager' database
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to create connection to the taskmanager database", e);
            throw e;  // Re-throw the exception to be handled outside
        }
    }

    // Close the connection manually (JDBC does not handle this automatically)
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Failed to close connection", e);
            }
        }
    }
}
