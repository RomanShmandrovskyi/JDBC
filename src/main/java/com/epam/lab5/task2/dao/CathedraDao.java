package com.epam.lab5.task2.dao;

import com.epam.lab5.task2.entity.Cathedra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CathedraDao {
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    public void add(int addressId, Cathedra cathedra, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO cathedra(cathedra_name, cathedra_email, " +
                "cathedra_phone, cathedra_description, cathedra_address) VALUES (?,?,?,?,?)");

        preparedStatement.setString(1, cathedra.getCathedraName());
        preparedStatement.setString(2, cathedra.getCathedraEmail());
        preparedStatement.setString(3, cathedra.getCathedraPhone());
        preparedStatement.setString(4, cathedra.getCathedraDescription());
        preparedStatement.setInt(5, addressId);

        preparedStatement.execute();
    }

    public Cathedra findCathedraById(int id, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM cathedra WHERE cathedra_id = ?");

        preparedStatement.setInt(1, id);
        rs = preparedStatement.executeQuery();

        if(rs.next()){
            return new Cathedra(rs.getInt("cathedra_id"),
                    rs.getString("cathedra_name"),
                    rs.getString("cathedra_email"),
                    rs.getString("cathedra_phone"),
                    rs.getString("cathedra_description"),
                    rs.getInt("cathedra_address")
            );
        }else {
            return null;
        }
    }

    public void edit(Cathedra cathedra, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE cathedra SET cathedra_name = ?, cathedra_email = ?, " +
                "cathedra_phone = ?, cathedra_description = ?, cathedra_address = ? WHERE cathedra_id = ?");

        preparedStatement.setString(1, cathedra.getCathedraName());
        preparedStatement.setString(2, cathedra.getCathedraEmail());
        preparedStatement.setString(3, cathedra.getCathedraPhone());
        preparedStatement.setString(4, cathedra.getCathedraDescription());
        preparedStatement.setInt(5, cathedra.getAddressId());
        preparedStatement.setInt(6, cathedra.getCathedraId());

        preparedStatement.executeUpdate();
    }

    public void delete(int id, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM cathedra WHERE cathedra_id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Cathedra> getAllCathedras(Connection connection) throws SQLException {
        List<Cathedra> cathedraList = new ArrayList<Cathedra>();

        preparedStatement = connection.prepareStatement("SELECT * FROM cathedra");

        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            cathedraList.add(new Cathedra(rs.getInt("cathedra_id"),
                    rs.getString("cathedra_name"),
                    rs.getString("cathedra_email"),
                    rs.getString("cathedra_phone"),
                    rs.getString("cathedra_description"),
                    rs.getInt("cathedra_address")));
        }

        return cathedraList;
    }

    public int getCathedraId(Connection connection, Cathedra cathedra) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM cathedra WHERE cathedra_name LIKE ? AND cathedra_description LIKE ? " +
                "AND cathedra_email LIKE ? AND cathedra_phone LIKE ?");

        preparedStatement.setString(1, cathedra.getCathedraName());
        preparedStatement.setString(2, cathedra.getCathedraDescription());
        preparedStatement.setString(3, cathedra.getCathedraEmail());
        preparedStatement.setString(4, cathedra.getCathedraPhone());

        rs = preparedStatement.executeQuery();

        rs.next();

        Cathedra currentCathedra = new Cathedra(rs.getInt("cathedra_id"));

        return currentCathedra.getCathedraId();
    }

    public Cathedra findCathedraWithSameAddress(int addressId, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM cathedra WHERE cathedra_address = ?");
        preparedStatement.setInt(1, addressId);
        rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Cathedra(rs.getInt("cathedra_id"),
                    rs.getString("cathedra_name"),
                    rs.getString("cathedra_email"),
                    rs.getString("cathedra_phone"),
                    rs.getString("cathedra_description"),
                    rs.getInt("cathedra_address"));
        } else return null;
    }

    public void updateCathedraAddressId(Cathedra cathedra, int id, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE cathedra SET cathedra_address = ? WHERE cathedra_id = ?");

        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, cathedra.getCathedraId());

        preparedStatement.executeUpdate();
    }

    public List<Cathedra> getAllCathedrasExpectOnes(int cathedraId, Connection connection) throws SQLException {
        List<Cathedra> cathedraList = new ArrayList<Cathedra>();

        preparedStatement = connection.prepareStatement("SELECT * FROM cathedra WHERE cathedra_id <> ?");
        preparedStatement.setInt(1, cathedraId);
        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            cathedraList.add(new Cathedra(rs.getInt("cathedra_id"),
                    rs.getString("cathedra_name"),
                    rs.getString("cathedra_email"),
                    rs.getString("cathedra_phone"),
                    rs.getString("cathedra_description"),
                    rs.getInt("cathedra_address")));
        }

        return cathedraList;
    }

    public void showMessage(String message){
        System.out.println(message);
    }
}