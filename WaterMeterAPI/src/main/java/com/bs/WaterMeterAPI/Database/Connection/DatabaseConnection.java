package com.bs.WaterMeterAPI.Database.Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/WaterMeter";
        String username = "root";
        String password = "Marta3101!";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC-Treiber nicht gefunden.");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Verbindung zur MySQL-Datenbank hergestellt");
        } catch (SQLException e) {
            System.err.println("Fehler beim Herstellen der Verbindung zur Datenbank: " + e.getMessage());
        }

        return connection;
    }

    public static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Verbindung zur MySQL-Datenbank geschlossen");
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Verbindung zur Datenbank: " + e.getMessage());
            }
        }
    }
}
