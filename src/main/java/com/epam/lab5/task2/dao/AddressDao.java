package com.epam.lab5.task2.dao;

import com.epam.lab5.task2.entity.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDao {
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    //додавання адреси в бд
    public void add(Address address, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO address(country, region, city, " +
                "street, building, flat) VALUES (?,?,?,?,?,?)");

        preparedStatement.setString(1, address.getCountry());
        preparedStatement.setString(2, address.getRegion());
        preparedStatement.setString(3, address.getCity());
        preparedStatement.setString(4, address.getStreet());
        preparedStatement.setString(5, address.getBuilding());
        preparedStatement.setString(6, address.getFlat());

        preparedStatement.execute();
    }

    //знаходження адреси по id (повертає об'єкт адреси)
    public Address findAddressById(int id, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM address WHERE address_id = ?");

        preparedStatement.setInt(1, id);
        rs = preparedStatement.executeQuery();

        if(rs.next()){
            return new Address(rs.getInt("address_id"),
                    rs.getString("country"),
                    rs.getString("region"),
                    rs.getString("city"),
                    rs.getString("street"),
                    rs.getString("building"),
                    rs.getString("flat"));
        }else {
            return null;
        }
    }

    //апдейт адреси
    public void edit(Address address, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE address SET country = ?, region = ?, " +
                "city = ?, street = ?, building = ?, flat = ? WHERE address_id = ?");

        preparedStatement.setString(1, address.getCountry());
        preparedStatement.setString(2, address.getRegion());
        preparedStatement.setString(3, address.getCity());
        preparedStatement.setString(4, address.getStreet());
        preparedStatement.setString(5, address.getBuilding());
        preparedStatement.setString(6, address.getFlat());
        preparedStatement.setInt(7, address.getAddressId());

        preparedStatement.executeUpdate();
    }

    //видалення адреси
    public void delete(int id, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM address WHERE address_id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    //отримати id адреси
    public int getAddressId(Address address, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM address WHERE country LIKE ? AND " +
                "region LIKE ? AND city LIKE ? AND street LIKE ? AND building LIKE ? AND flat LIKE ?");

        preparedStatement.setString(1, address.getCountry());
        preparedStatement.setString(2, address.getRegion());
        preparedStatement.setString(3, address.getCity());
        preparedStatement.setString(4, address.getStreet());
        preparedStatement.setString(5, address.getBuilding());
        preparedStatement.setString(6, address.getFlat());

        rs = preparedStatement.executeQuery();

        rs.next();

        Address currentAddress = new Address(rs.getInt("address_id"));

        return currentAddress.getAddressId();
    }

    //отримати всі адреси
    public List<Address> getAllAddresses(Connection connection) throws SQLException {
        List<Address> addressList = new ArrayList<Address>();

        preparedStatement = connection.prepareStatement("SELECT * FROM address");
        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            addressList.add(new Address(rs.getInt("address_id"),
                    rs.getString("country"),
                    rs.getString("region"),
                    rs.getString("city"),
                    rs.getString("street"),
                    rs.getString("building"),
                    rs.getString("flat")));
        }
        return  addressList;
    }

    public void showMessage(String message){
        System.out.println(message);
    }
}