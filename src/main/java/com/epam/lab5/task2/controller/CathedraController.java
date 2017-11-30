package com.epam.lab5.task2.controller;

import com.epam.lab5.task2.entity.Cathedra;
import com.epam.lab5.task2.services.CathedraService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

class CathedraController {
    static void startCathedraController() throws SQLException {
        MainController mainController = new MainController();
        Connection connection = mainController.connection;
        CathedraService cathedraService = new CathedraService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Cathedra Table!");
            System.out.println("\nEnter:\n1 - add new cathedra\n2 - find cathedra by id" +
                    "\n3 - edit cathedra\n4 - delete cathedra" +
                    "\n5 - get all cathedras\n6 - return to main menu");
            System.out.print("Your choice: ");
            int switcher = sc.nextInt();

            int size = cathedraService.getAllCathedras(connection).size();

            if (switcher == 1) {
                cathedraService.add(connection);//додавання кафедри
            } else if (switcher == 2) {
                if (size > 0) {
                    cathedraService.findCathedraById(connection);//знайти кафедру по ід
                } else {
                    System.out.println("Nothing to find!");
                }
            } else if (switcher == 3) {
                if (size > 0) {
                    cathedraService.edit(connection);//апдейтнути кафедру
                } else {
                    System.out.println("Nothing to update!");
                }
            } else if (switcher == 4) {
                if (size > 0) {
                    cathedraService.delete(connection);//видалити кафедру
                } else {
                    System.out.println("Nothing to delete!");
                }
            } else if (switcher == 5) {
                if (size > 0) {
                    for (Cathedra c : cathedraService.getAllCathedras(connection)) {//отримати всі кафедри
                        System.out.println();
                        System.out.println(c);
                    }
                } else {
                    System.out.println("Nothing to show...");
                }
            } else if (switcher == 6) {
                break;
            }
        }
    }
}
