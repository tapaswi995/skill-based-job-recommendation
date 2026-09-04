package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Update these values for your local MySQL installation. */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/job_recommendation_system?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // change this before running

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
