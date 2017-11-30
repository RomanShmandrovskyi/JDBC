package com.epam.lab5.task3;

import com.epam.lab5.task2.entity.Address;
import com.epam.lab5.task2.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.
                getConnection("jdbc:mysql://127.0.0.1:3306/university?autoReconnect=true&useSSL=false", "root", "root");

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        connection.setAutoCommit(false);

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE student_id BETWEEN 20 AND 60");
            resultSet = preparedStatement.executeQuery();

            List<Student> students = new ArrayList<Student>();

            while (resultSet.next()){
                students.add(new Student(resultSet.getInt("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("student_surname"),
                        resultSet.getString("student_gender"),
                        resultSet.getString("student_birth_day"),
                        resultSet.getString("student_phone"),
                        resultSet.getInt("student_exam_book_number"),
                        resultSet.getString("student_start_day"),
                        resultSet.getString("student_study_form"),
                        resultSet.getInt("student_course"),
                        resultSet.getInt("student_address"),
                        resultSet.getInt("cathedra_id")));
            }

            System.out.println("=====================STUDENTS=====================");
            for (Student s : students){
                System.out.println(s);
                System.out.println();
            }

            preparedStatement = connection.prepareStatement("SELECT * FROM address WHERE street LIKE ?");
            preparedStatement.setString(1, "Kopernyka");
            resultSet = preparedStatement.executeQuery();

            List<Address> addresses = new ArrayList<Address>();
            while (resultSet.next()) {
                addresses.add(new Address(resultSet.getInt("address_id"),
                        resultSet.getString("country"),
                        resultSet.getString("region"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getString("building"),
                        resultSet.getString("flat")));
            }

            System.out.println("=====================ADDRESSES=====================");
            for(Address a : addresses){
                System.out.println(a);
                System.out.println();
            }

            connection.commit();
        }catch (SQLException e){
            connection.rollback();
        }
    }
}