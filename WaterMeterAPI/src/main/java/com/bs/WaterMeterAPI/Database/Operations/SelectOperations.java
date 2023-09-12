package com.bs.WaterMeterAPI.Database.Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bs.WaterMeterAPI.Database.Connection.DatabaseConnection;

public class SelectOperations {

    public static List<String> getUserMails(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<String> mails = new ArrayList<>(); 

        try {
            // SQL-Abfrage erstellen
            String sqlQuery = "SELECT email FROM user"; // Annahme, dass die Tabelle User heißt

            // Statement erstellen und SQL-Abfrage ausführen
            connection = DatabaseConnection.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            // E-Mails ausgeben
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                mails.add(email);
            }
            System.out.println("Abfrage auf die Datenbank erfolgreich ausgeführt");
            return mails;
        } catch (SQLException e) {
            System.err.println("Fehler bei der Datenbankabfrage: " + e.getMessage());
            return mails;
        } finally {
            // Ressourcen freigeben und Verbindung schließen
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                DatabaseConnection.disconnect(connection);
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Verbindung oder der Ressourcen: " + e.getMessage());
            }
        }
    }



    public static boolean isUserValid(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isValid = false;

        try {
            // SQL-Abfrage erstellen
            String sqlQuery = "SELECT COUNT(*) AS user_count FROM user WHERE email = ? AND password = ?";
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // SQL-Abfrage ausführen und das Ergebnis abrufen
            resultSet = preparedStatement.executeQuery();

            // Überprüfen, ob ein Benutzer gefunden wurde
            if (resultSet.next()) {
                int userCount = resultSet.getInt("user_count");
                isValid = (userCount > 0);
            }
            return isValid;
        } catch (SQLException e) {
            System.err.println("Fehler bei der Datenbankabfrage: " + e.getMessage());
            return false;
        } finally {
            // Ressourcen freigeben und Verbindung schließen
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DatabaseConnection.disconnect(connection);
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Verbindung oder der Ressourcen: " + e.getMessage());
            }
        }
    }

    public static String getUUIDByEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL-Abfrage erstellen
            String sqlQuery = "SELECT uuid FROM users WHERE email = ?";
            connection = DatabaseConnection.connect(); // Stellen Sie die Verbindung zur Datenbank her
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);

            // SQL-Abfrage ausführen und das Ergebnis abrufen
            resultSet = preparedStatement.executeQuery();

            // Überprüfen, ob ein Datensatz gefunden wurde
            if (resultSet.next()) {
                String uuidStr = resultSet.getString("uuid");
                return uuidStr;
            } else {
                return null; // Kein Benutzer mit dieser E-Mail gefunden
            }
        } catch (SQLException e) {
            System.err.println("Fehler bei der Datenbankabfrage: " + e.getMessage());
            return null;
        } finally {
            // Ressourcen freigeben und Verbindung schließen
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DatabaseConnection.disconnect(connection);
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Verbindung oder der Ressourcen: " + e.getMessage());
            }
        }
    }


}