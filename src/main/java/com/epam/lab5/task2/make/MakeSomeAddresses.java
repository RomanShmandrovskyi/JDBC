package com.epam.lab5.task2.make;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class MakeSomeAddresses {
    public static void main(String[] args) throws SQLException {
        Random random = new Random();
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/university?autoReconnect=true&useSSL=false", "root", "root");

        PreparedStatement preparedStatement;

        String[] cities = {"Lviv", "Drohobych", "Zhovkva", "Sambir", "Stryi"};
        String[] streets = {"Franka", "Konovaltsia", "V. Velygoho", "Puliuya", "Kopernyka", "O. Honchara"};

        for (int i = 0; i < 30; i++) {
            preparedStatement = connection.prepareStatement("INSERT INTO address(country, region, city, " +
                    "street, building, flat) VALUES (?,?,?,?,?,?)");

            preparedStatement.setString(1, "Ukraine");
            preparedStatement.setString(2, "Lviv");
            preparedStatement.setString(3, cities[random.nextInt(cities.length)]);
            preparedStatement.setString(4, streets[random.nextInt(streets.length)]);
            int building = random.nextInt(120)+1;
            preparedStatement.setString(5, String.valueOf(building));
            int flat = random.nextInt(100)+1;
            preparedStatement.setString(6, String.valueOf(flat));

            preparedStatement.execute();
        }
    }
}
