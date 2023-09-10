package com.bs.WaterMeterAPI.Database.Operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
}
