package com.epam.lab5.task2.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainController {
    Connection connection = DriverManager
            .getConnection("jdbc:mysql://127.0.0.1:3306/university?autoReconnect=true&useSSL=false", "root", "root");

    MainController() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {
        MainController mainController = new MainController();

        Connection connection = mainController.connection;

        Scanner sc = new Scanner(System.in);

        int switcher;

        System.out.println("WELCOME TO UNIVERSITY INC.");

        while (true) {
            System.out.println("Main menu:\nDB has 4 section:\n1 - Addresses\n2 - Cathedras" +
                    "\n3 - Students\n4 - Subjects\n5 - Exit from app");
            System.out.print("Enter your choice: ");
            switcher = sc.nextInt();
            if (switcher == 1) {
                AddressController.startAddressController();
            } else if (switcher == 2) {
                CathedraController.startCathedraController();
            } else if (switcher == 3) {
                StudentController.startStudentController();
            } else if (switcher == 4) {
                SubjectController.startSubjectController();
            } else {
                System.out.println("\nGood bay! Have a nice day!");
                connection.close();
                System.exit(0);
            }
            System.out.println("\nWelcome back!");
        }
    }
}