package com.epam.lab5.task2.make;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class MakeSomeStudents {
    public static void main(String[] args) throws SQLException {
        Random random = new Random();
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/university?autoReconnect=true&useSSL=false", "root", "root");

        PreparedStatement preparedStatement;
        String[] names = {"Ivan", "Andriy", "Petro", "Bohdan", "Roman", "Ira", "Olena", "Olia", "Anya", "Nadia"};
        String[] surnames = {"Pupkin", "Hilary", "Poroshenko", "Yanukovych", "Yaceniuk", "Shmandrovskyi", "Pavlenko", "Savchyn", "Barabash", "Lychtei"};
        String[] gender = {"male", "female"};
        String[] studyForm = {"full", "part"};

        for (int i = 0; i < 100; i++) {
            preparedStatement = connection.prepareStatement("INSERT INTO student(student_name, student_surname, " +
                    "student_gender, student_phone, " +
                    "student_study_form, student_course, " +
                    "student_address, cathedra_id) " +
                    "VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, names[random.nextInt(names.length)]);
            preparedStatement.setString(2, surnames[random.nextInt(surnames.length)]);
            preparedStatement.setString(3, gender[random.nextInt(gender.length)]);
            int phone = random.nextInt(1000000000) + 1000000000;
            preparedStatement.setString(4, String.valueOf(String.format("+%s", phone)));
            preparedStatement.setString(5, studyForm[random.nextInt(studyForm.length)]);
            preparedStatement.setInt(6, random.nextInt(6) + 1);
            preparedStatement.setInt(7, random.nextInt(30) + 6);
            preparedStatement.setInt(8, random.nextInt(5) + 1);

            preparedStatement.execute();
        }
    }
}
