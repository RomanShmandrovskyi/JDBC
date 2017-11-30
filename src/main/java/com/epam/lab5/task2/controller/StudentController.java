package com.epam.lab5.task2.controller;

import com.epam.lab5.task2.entity.Student;
import com.epam.lab5.task2.services.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

class StudentController {
    static void startStudentController() throws SQLException {
        MainController mainController = new MainController();
        Connection connection = mainController.connection;

        StudentService studentService = new StudentService();

        while (true) {
            System.out.println("Welcome to student table!");
            System.out.println("Enter:\n1 - add new student" +
                    "\n2 - find student by id\n3 - edit student" +
                    "\n4 - delete student\n5 - get all students of current cathedra" +
                    "\n6 - get all students\n7 - return to main menu");
            System.out.print("Your choice: ");
            Scanner sc = new Scanner(System.in);
            int switcher = sc.nextInt();

            int size = studentService.getAllStudents(connection).size();

            if (switcher == 1) {//додати студента
                studentService.add(connection);
            } else if (switcher == 2) {//знайти студента по id
                if(size>0){
                    studentService.findStudentById(connection);
                }else {
                    System.out.println("Nothing to find!");
                }
            } else if (switcher == 3) {//апдейт студента

            } else if (switcher == 4) {//видалити студента

            } else if (switcher == 5) {//отримати всіх студентів
                if(size>0){
                    studentService.getAllStudentOfCurrentCathedra(connection);
                }else{
                    System.out.println();
                }
            } else if (switcher == 6) {//отримати всіх студентів з кафедри
                if (size>0){
                    for(Student s : studentService.getAllStudents(connection)){
                        System.out.println();
                        System.out.println(s);
                    }
                }else {
                    System.out.println("Nothing to show...");
                }
            } else if (switcher == 7) {
                break;
            }
        }
    }
}
