package com.epam.lab5.task2.controller;

import com.epam.lab5.task2.entity.Subject;
import com.epam.lab5.task2.services.SubjectService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

class SubjectController {
    static void startSubjectController() throws SQLException {
        MainController mainController = new MainController();
        Connection connection = mainController.connection;

        SubjectService subjectService = new SubjectService();

        while (true) {
            System.out.println("Welcome to Subjects table!");
            System.out.println("\nEnter:\n1 - add new subject\n2 - find subject by id" +
                    "\n3 - edit subject\n4 - delete subject" +
                    "\n5 - get all subjects of current cathedra" +
                    "\n6 - get all subjects\n7 - return to main menu");
            System.out.print("Your choice: ");
            Scanner sc = new Scanner(System.in);
            int switcher = sc.nextInt();

            int size = subjectService.getAllSubjects(connection).size();

            if (switcher == 1) {
                subjectService.add(connection);
            } else if (switcher == 2) {
                if (size > 0) {
                    subjectService.findSubjectById(connection);
                } else {
                    System.out.println("Nothing to find!");
                }
            } else if (switcher == 3) {
                if (size > 0) {
                    subjectService.edit(connection);
                } else {
                    System.out.println("Nothing to edit");
                }
            } else if (switcher == 4) {
                if (size > 0) {
                    subjectService.delete(connection);
                } else {
                    System.out.println("Nothing to delete!");
                }
            } else if (switcher == 5) {
                if (size > 0) {
                    subjectService.getAlSubjectOfCurrentCathedra(connection);
                } else {
                    System.out.println("Nothing to show...");
                }
            } else if (switcher == 6) {
                if (size > 0) {
                    for (Subject s : subjectService.getAllSubjects(connection)) {
                        System.out.println(s);
                        System.out.println();
                    }
                } else {
                    System.out.println("Nothing to show...");
                }
            } else if (switcher == 7) {
                break;
            }
        }
    }
}