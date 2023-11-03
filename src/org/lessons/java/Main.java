package org.lessons.java;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8889/db-nations";
        String user = "root";
        String password = "root";

        // creo la connessione
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // scrivo la query e la assegno
            String query = "SELECT c.country_id,c.name as Country_name, r.name as Region_name, c2.name as Continent_name FROM countries c JOIN regions r ON r.region_id = c.region_id  JOIN continents c2 ON c2.continent_id =r.continent_id ORDER BY c.name ASC;";
            //creo una statement sql e eseguo la query
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String countryId = resultSet.getString("country_id");
                        String countryName = resultSet.getString("Country_name");
                        String regionName = resultSet.getString("Region_name");
                        String continentName = resultSet.getString("Continent_name");
                        System.out.println(countryId + " " + countryName + " " + regionName + " " + continentName);
                    }
                } catch (SQLException e) {
                    System.out.println("unable to execute query");
                }
            } catch (SQLException e) {
                System.out.println("unable to create Statement");
            }
        } catch (SQLException e) {
            System.out.println("unable to connect to database");
            e.printStackTrace();
        }
    }
}
