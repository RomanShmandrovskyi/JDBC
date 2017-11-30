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

public class AddressService {
    private AddressDao addressDao = new AddressDao();
    private CathedraDao cathedraDao = new CathedraDao();
    private StudentDao studentDao = new StudentDao();
    private Scanner scanner = new Scanner(System.in);

    //додати нову адресу
    public Address add(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);

        //ввід адреси з консолі
        System.out.print("Enter country: ");
        String country = sc.nextLine();

        System.out.print("Enter region: ");
        String region = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        System.out.print("Enter street: ");
        String street = sc.nextLine();

        System.out.print("Enter building: ");
        String building = sc.nextLine();

        System.out.print("Enter flat: ");
        String flat = sc.nextLine();

        //створення об'єкту адреси
        Address address = new Address(country, region, city, street, building, flat);

        //додавання адреси в БД
        addressDao.add(address, connection);

        System.out.println("Address added successfully!");

        return address;
    }

    //знайти адресу по введеному ід
    public Address findAddressById(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter address id: ");

        int id = checkInput(sc);//введення ід

        Address address = addressDao.findAddressById(id, connection);//знаходження адреси по введеному ід

        if (address != null) {//якщо існує - виводжу на екран
            System.out.println(address);
        } else {//якщо не існує - помилка
            System.out.println("Address with such id doesn't exist");
        }

        return address;
    }

    //апдейт адреси по ід
    public Address edit(int cathedraAddressId, Connection connection) throws SQLException {
        Address address;
        int choice;

        address = addressDao.findAddressById(cathedraAddressId, connection); //пошук адреси за якою закріплена кафедра
        System.out.println("Old address: ");
        System.out.println(address);

        //вивід меню апдейту об'єкту (поки не save (7))
        while (true) {
            System.out.print("Enter:\n1 - to update country" +
                    "\n2 - to update region" +
                    "\n3 - to update city" +
                    "\n4 - to update street" +
                    "\n5 - to update building" +
                    "\n6 - to update flat" +
                    "\n7 - to save" +
                    "\nYour choice: ");
            choice = checkInput(scanner);
            Scanner sc = new Scanner(System.in);
            if (choice == 1) {
                System.out.print("Enter new country: ");
                String s = sc.nextLine();
                address.setCountry(s);
            } else if (choice == 2) {
                System.out.print("Enter new region: ");
                String s = sc.nextLine();
                address.setRegion(s);
            } else if (choice == 3) {
                System.out.print("Enter new city: ");
                String s = sc.nextLine();
                address.setCity(s);
            } else if (choice == 4) {
                System.out.print("Enter new street: ");
                String s = sc.nextLine();
                address.setStreet(s);
            } else if (choice == 5) {
                System.out.print("Enter new building: ");
                String s = sc.nextLine();
                address.setBuilding(s);
            } else if (choice == 6) {
                System.out.print("Enter new flat: ");
                String s = sc.nextLine();
                address.setFlat(s);
            } else if (choice == 7) {
                break;
            }
            System.out.println("OK!\n");
        }

        //апдейт старої адреси
        addressDao.edit(address, connection);

        System.out.println("Saved!");

        System.out.println("New address is:");
        //вивід нової адреси
        System.out.println(address);

        return address;
    }

    //перевірка чи адреса по заданому id існує (true - якщо існує, false - не існує)
    private boolean checkAddress(int addressId, Connection connection) throws SQLException {
        return addressDao.findAddressById(addressId , connection) != null;
    }

    private int checkInput(Scanner scanner){
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Invalid input! Try again: ");
        }

        return scanner.nextInt();
    }

    //розумне видалення адреси
    public boolean delete(Connection connection) throws SQLException {
        System.out.print("Enter address id to delete: ");

        int addressId = checkInput(scanner);

        if (!checkAddress(addressId, connection)) {
            return false;
        }

        //пошук кафедри де address_id рівний введеному
        Cathedra cathedra = cathedraDao.findCathedraWithSameAddress(addressId, connection);//пошук об'єкту кафедри у якого address_id така як було введено
        List<Student> studentList = studentDao.findStudentsWithSameAddress(addressId, connection);//пошук об'єкту студента у якого address_id як було ввдено
        int size = studentList.size();
        int choice;
        if (cathedra != null) {//якщо така кафедра існує
            System.out.println("By this address attached cathedra: " + cathedra.getCathedraName());
            System.out.print("Choose what you wont to do:\n1 - delete address and attached cathedra" +
                    "\n2 - create new address for this cathedra\n3 - exit to menu\nYou choice: ");

            choice = checkInput(scanner);

            if (choice == 1) {//видалення адреси з введеним ід а також усіх даних по зовнішньому ключу
                addressDao.delete(addressId, connection);
                System.out.println("Deleted!");
            } else if (choice == 2) {//створення нової адреси (та сет до нової кафедри) + видалення старої адреси
                Address address = add(connection);//створення нового об'єкту адреси через метод додавання
                int id = addressDao.getAddressId(address, connection);//отримання ід новоствореної адреси
                cathedraDao.updateCathedraAddressId(cathedra, id, connection);//сет ід нової адреси в кафедрі
                addressDao.delete(addressId, connection);//видалення старої адреси
                System.out.println("Deleted!");
            } else if (choice == 3) {//вихід в меню
                System.out.println();
            }
        } else if (size > 0) {
            System.out.println(String.format("By this address attached %s students\n", size));
            System.out.print("Choose what you wont to do:\n1 - delete address and attached student" +
                    "\n2 - create new address for this student\n3 - exit to menu\nYou choice: ");

            choice = checkInput(scanner);

            if (choice == 1) {//видалення адреси з введеним ід а також усіх даних по зовнішньому ключу
                addressDao.delete(addressId, connection);
                System.out.println("Deleted!");
            } else if (choice == 2) {//створення нової адреси (та сет до студента) + видалення старої адреси
                Address address = add(connection);//створення нового об'єкту адреси через метод додавання
                int id = addressDao.getAddressId(address, connection);//отримання ід новоствореної адреси
                studentDao.updateStudentAddressId(studentList, id, connection);//сет ід нової адреси до студента
                addressDao.delete(addressId, connection);//видалення старої адреси
                System.out.println("Deleted!");
            } else if (choice == 3) {//вихід в меню
                System.out.println();
            }
        } else {//якщо адреса не має зовнішніх зв'язків - видаллення
            addressDao.delete(addressId, connection);
            System.out.println("Deleted!");
        }
        return true;
    }

    //отримати всі адреси
    public List<Address> getAllAddress(Connection connection) throws SQLException {
        return new ArrayList<Address>(addressDao.getAllAddresses(connection));
    }

    //отрима ід адреси
    int getAddressId(Address address, Connection connection) throws SQLException {
        return addressDao.getAddressId(address, connection);
    }
}
