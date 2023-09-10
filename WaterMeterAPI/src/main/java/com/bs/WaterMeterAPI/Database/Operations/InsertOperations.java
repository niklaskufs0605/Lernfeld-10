package com.bs.WaterMeterAPI.Database.Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bs.WaterMeterAPI.Database.Connection.DatabaseConnection;

public class InsertOperations {
    public static void insertUser(String email, String password, String uuid){
        // Create connection to Database
        Connection connection = DatabaseConnection.connect();
        // SQL-Statement
        String insertUserStatement = "INSERT INTO user (email, password, uuid) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserStatement);
            
            // Daten für das Insert-Statement festlegen
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, uuid);

            // Insert-Statement ausführen
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " Datensatz erfolgreich eingefügt.");
            } else {
                System.out.println("Keine Datensätze eingefügt.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Fehler beim Einfügen der Daten: " + e.getMessage());
        } finally {
            DatabaseConnection.disconnect(connection);
        }
    }
}
