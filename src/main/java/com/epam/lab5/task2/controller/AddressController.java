package com.epam.lab5.task2.controller;

import com.epam.lab5.task2.entity.Address;
import com.epam.lab5.task2.services.AddressService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

class AddressController {
    static void startAddressController() throws SQLException {
        MainController mainController = new MainController();
        Connection connection = mainController.connection;

        AddressService addressService = new AddressService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Address Table!");
            System.out.println("Enter:\n1 - find address by id" +
                    "\n2 - delete address\n3 - get all addresses\n4 - return to main menu");
            System.out.print("Your choice: ");
            int switcher = sc.nextInt();

            int size = addressService.getAllAddress(connection).size();

            if (switcher == 1) {
                if (size > 0) {
                    addressService.findAddressById(connection);//знайти по ід
                } else {
                    System.out.println("Nothing to find!");
                }
            } else if (switcher == 2) {
                if (size > 0) {
                    addressService.delete(connection);//видалити по ід
                } else {
                    System.out.println("Nothing to delete!");
                }
            } else if (switcher == 3) {
                if (size > 0) {
                    for (Address a : addressService.getAllAddress(connection)) {//отримати все
                        System.out.println();
                        System.out.println(a);
                    }
                } else {
                    System.out.println("Nothing to show...");
                }
            } else if (switcher == 4) {
                break;//вихід
            }
        }
    }
}