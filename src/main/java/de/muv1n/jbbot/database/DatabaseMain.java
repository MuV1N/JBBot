package de.muv1n.jbbot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMain {

    private final Connection connection;
    public DatabaseMain() throws SQLException {
        System.out.println("Try to connect to Database...");
        String connectionString = "jdbc:mariadb://localhost:3306/";
        this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/", "MuV1N", "MaKl1910");
        System.out.println("Database... connected to: " + connectionString + " , User: MuV1N");
    }

    public Connection getConnection() {
        return connection;
    }
}
