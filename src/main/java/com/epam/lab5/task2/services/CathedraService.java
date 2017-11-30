package com.epam.lab5.task2.services;

import com.epam.lab5.task2.dao.AddressDao;
import com.epam.lab5.task2.dao.CathedraDao;
import com.epam.lab5.task2.dao.StudentDao;
import com.epam.lab5.task2.entity.Address;
import com.epam.lab5.task2.entity.Cathedra;
import com.epam.lab5.task2.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CathedraService {
    private CathedraDao cathedraDao = new CathedraDao();
    private AddressService addressService = new AddressService();
    private AddressDao addressDao = new AddressDao();
    private StudentDao studentDao = new StudentDao();
    private Scanner scanner = new Scanner(System.in);

    //додавання нової кафедри
    public Cathedra add(Connection connection) throws SQLException {
        System.out.print("Enter cathedra name: ");
        String name = scanner.nextLine();

        System.out.print("Enter cathedra email: ");
        String email = scanner.nextLine();

        System.out.print("Enter cathedra phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter cathedra description: ");
        String description = scanner.nextLine();

        //створення нової адреси для кафедри та сет її ід
        System.out.println("Create new address for this cathedra: ");
        System.out.println();

        Address address = addressService.add(connection);//створення нової адреси
        Cathedra cathedra = new Cathedra(name, email, phone, description, address);//створення об'єкту кафедри з адресою
        cathedraDao.add(addressDao.getAddressId(address, connection), cathedra, connection);//давання нової кафедри в БД
        System.out.println("Cathedra added successfully!");

        return cathedra;
    }

    //отримати кафедру по ід
    public Cathedra findCathedraById(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter cathedra id: ");
        while (!sc.hasNextInt()) {//отримання кафедри по ід
            sc.next();
            System.out.print("Invalid input! Try again: ");//чек на невірний ввід
        }
        int id = sc.nextInt();//введення ід

        Cathedra cathedra = cathedraDao.findCathedraById(id, connection);//пошук об'єкту кафедри з введеним ід
        if (cathedra != null) { //якшо існує - виводжу на екран
            System.out.println(cathedra);
        } else {//якщо не існує - повідомлення про помилку
            System.out.println("Cathedra with such id doesn't exist");
        }
        return cathedra;
    }

    //апдейт кафедри
    public void edit(Connection connection) throws SQLException {
        Cathedra cathedra;
        int cathedraId, choice;

        while (true) {
            System.out.print("Enter cathedra id to update: ");

            while (!scanner.hasNextInt()) {//вибір
                scanner.next();
                System.out.print("Invalid input! Try again: ");//чек на невірний ввід
            }
            cathedraId = scanner.nextInt();

            cathedra = cathedraDao.findCathedraById(cathedraId, connection); //старий об'єкт
            if (cathedra != null) {
                System.out.println("Old cathedra: ");
                System.out.println(cathedra);
                break;
            } else {
                //якщо кафедри не існує
                System.out.println("cathedra with such id doesn't exist");
            }
        }

        while (true) {
            System.out.print("Enter:\n1 - to update cathedra name" +
                    "\n2 - to update cathedra email" +
                    "\n3 - to update cathedra phone" +
                    "\n4 - to update cathedra description" +
                    "\n5 - to update cathedra address" +
                    "\n6 - to save" +
                    "\nYour choice: ");
            choice = scanner.nextInt();
            Scanner sc = new Scanner(System.in);
            if (choice == 1) {
                System.out.print("Enter new name: ");
                String s = sc.nextLine();
                cathedra.setCathedraName(s);
            } else if (choice == 2) {
                System.out.print("Enter new email: ");
                String s = sc.nextLine();
                cathedra.setCathedraEmail(s);
            } else if (choice == 3) {
                System.out.print("Enter new phone : ");
                String s = sc.nextLine();
                cathedra.setCathedraPhone(s);
            } else if (choice == 4) {
                System.out.print("Enter new description: ");
                String s = sc.nextLine();
                cathedra.setCathedraDescription(s);
            } else if (choice == 5) {
                //редагування адреси кафедри
                Address address = addressService.edit(cathedraId, connection);
                cathedra.setAddress(address);
            } else if (choice == 6) {
                break;
            }
            System.out.println("OK!\n");
        }

        //апдейт кафедри
        cathedraDao.edit(cathedra, connection);

        System.out.println("Saved!");

        System.out.println(cathedra);
    }

    //перевірка чи кафедра по заданому id існує (true - якщо існує, false - не існує)
    private boolean check(int cathedraId, Connection connection) throws SQLException {
        return cathedraDao.findCathedraById(cathedraId, connection) != null;
    }

    //розумне видалення кафедри
    public boolean delete(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter cathedra id to delete: ");

        int cathedraId;//ід кафедри

        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Invalid input! Try again: ");//чек на невірний ввід
        }
        cathedraId = sc.nextInt();

        //якщо кафедри з введеним id не існує - виходимо з методу
        if(!check(cathedraId, connection)){
            System.out.println("Cathedra with such id doesn't exist");
            return false;
        }

        //якщо кафедра з введеним id існує - продовжуємо виконання методу

        //пошук усіх студентів у яких номер кафедри співпадає з введеним
        List<Student> studentWithCurrentCathedra = new ArrayList<Student>(studentDao
                .getAllStudentsWithCurrentCathedra(cathedraId, connection));

        int studentCount = studentWithCurrentCathedra.size();//чек кількості студентів

        if (studentCount > 0) {//якщо є студенти, що закріплені за даною кафедрою
            Scanner choiceScan = new Scanner(System.in);
            System.out.println(String.format("By this cathedra attached %s students!", studentCount));
            System.out.print("Choose what you wont to do:\n1 - delete cathedra and attached students" +
                    "\n2 - set to students new cathedra\n3 - exit to menu\nYou choice: ");

            while (!choiceScan.hasNextInt()) {
                choiceScan.next();
                System.out.print("Invalid input! Try again: ");//чек на невірний ввід
            }
            int choice = choiceScan.nextInt();//вибір дії

            if (choice == 1) {//видалити кафедру та все, що зв'язане з нею по зовнішніх ключах (тобто студентів та предмети)
                //видаляємо адресу, за якою закріплена кафедра
                addressDao.delete(cathedraDao.findCathedraById(cathedraId, connection).getAddressId(), connection);

                //видаляємо кафедру по введеному id
                cathedraDao.delete(cathedraId, connection);
            } else if (choice == 2) {//перекинути студентів на іншу кафедру
                Scanner cathedraIdScan = new Scanner(System.in);
                //ввести id кафедри на яку перекинути студентів
                System.out.println("Enter cathedra id for which you wont to move students: ");

                for (Cathedra c : cathedraDao.getAllCathedrasExpectOnes(cathedraId, connection)){
                    System.out.println(String.format("\t%s - %s", c.getCathedraId(), c.getCathedraName()));
                }

                System.out.print("Your choice: ");
                while (!sc.hasNextInt()) {
                    sc.next();
                    System.out.print("Invalid input! Try again: ");//чек на невірний ввід
                }

                //id нової кафедри для студентів
                int cathedraIdMove = cathedraIdScan.nextInt();

                //апдейт кафедри списку студентів
                studentDao.updateStudentAddressId(studentWithCurrentCathedra, cathedraIdMove, connection);

            } else {
                System.out.println();
            }
        } else {//якщо немає учнів на кафедрі - видаляємо кафедру та адресу
            //видаляємо адресу, за якою закріплена кафедра
            addressDao.delete(cathedraDao.findCathedraById(cathedraId, connection).getAddressId(), connection);
            //видаляємол кафедру
            cathedraDao.delete(cathedraId, connection);
            System.out.println("Done! Cathedra successfully deleted!");
        }
        return true;
    }

    //отримати усі кафедри
    public List<Cathedra> getAllCathedras(Connection connection) throws SQLException {
        return new ArrayList<Cathedra>(cathedraDao.getAllCathedras(connection));
    }
}