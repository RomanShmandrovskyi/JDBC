package com.epam.lab5.task4;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String columnNamePattern = null;
        String[] types = null;

        Connection connection = DriverManager.
                getConnection("jdbc:mysql://127.0.0.1:3306/university?autoReconnect=true&useSSL=false", "root", "root");

        ResultSet resultSet;

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("Pruduct version: " + databaseMetaData.getDatabaseProductVersion());

        System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
        System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());

        resultSet = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);

        List<String> tables = new ArrayList<String>();

        while (resultSet.next()){
            tables.add(resultSet.getString("TABLE_NAME"));
        }

        System.out.println(String.format("Database has %s tables", tables.size()));
        for (String s : tables){
            System.out.println(String.format("\t%s", s));
        }

        Statement statement = connection.createStatement();

        for (String table : tables){
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            System.out.println(String.format("Table \"%s\" has %s columns: ", table, resultSetMetaData.getColumnCount()));

            for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
                System.out.println(String.format("\t%s", resultSetMetaData.getColumnName(column)));
                System.out.println(String.format("\t\ttype: %s", resultSetMetaData.getColumnTypeName(column)));
                System.out.println(String.format("\t\tinNullable: %s", resultSetMetaData.isNullable(column)));
                System.out.println(String.format("\t\tisAutoIncrement: %s", resultSetMetaData.isAutoIncrement(column)));
            }
        }

        resultSet.close();
        connection.close();
    }
}
