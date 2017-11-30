package com.epam.lab5.task2.services;

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

public class StudentService {
    private static StudentDao studentDao = new StudentDao();
    private static CathedraDao cathedraDao = new CathedraDao();
    private static AddressService addressService = new AddressService();

    public Student add(Connection connection) throws SQLException {
        Scanner string = new Scanner(System.in);
        Scanner integer = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = string.nextLine();

        System.out.print("Enter student surname: ");
        String surname = string.nextLine();

        System.out.print("Enter student gender: ");
        String gender = string.nextLine();

        System.out.print("Enter student birthday (yyyy-mm-dd): ");
        String birth = string.nextLine();

        System.out.print("Enter student phone: ");
        String phone = string.nextLine();

        System.out.print("Enter student exam book number: ");
        int exBookNum = integer.nextInt();

        System.out.print("Enter date of starting studying (yyyy-mm-dd): ");
        String startDate = string.nextLine();

        System.out.print("Enter student study form: ");
        String studyForm = string.nextLine();

        System.out.print("Enter student course: ");
        int course = integer.nextInt();

        System.out.println("Create new address for student: ");
        System.out.println();

        Address address = addressService.add(connection);//створення нової адреси
        int addressId = addressService.getAddressId(address, connection);//отримання ід створеної адреси

        System.out.println("Choose cathedra for student from existing: ");

        for (Cathedra c : cathedraDao.getAllCathedras(connection)) {//перелік можливих кафедр
            System.out.println(String.format("\t%s - %s", c.getCathedraId(), c.getCathedraName()));
        }

        System.out.print("Chose cathedra: ");
        int cathedraId = integer.nextInt();

        Cathedra cathedra = cathedraDao.findCathedraById(cathedraId, connection);//знаходження об'єкту кафедри за введеним ід

        //створення об'єкту student
        Student student = new Student(name, surname, gender,
                birth, phone, exBookNum, startDate,
                studyForm, course, address, cathedra);

        studentDao.add(addressId, cathedraId, student, connection);

        System.out.println("Student added successfully\n");

        return student;
    }

    public Student findStudentById(Connection connection){
        Scanner sc = new Scanner(System.in);
        Student student = new Student();

        try{
            System.out.print("Enter student id: ");
            int studentId = sc.nextInt();
            student = studentDao.findStudentById(studentId, connection);
            System.out.println(student);
        } catch (SQLException e) {
            System.out.println("Student with such id does not exists");
        }

        return student;
    }

    public List<Student> getAllStudents(Connection connection) throws SQLException {
        return studentDao.getAllStudents(connection);
    }

    public List<Student> getAllStudentOfCurrentCathedra(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cathedra id: ");
        int cathedraId = scanner.nextInt();

        if(cathedraDao.findCathedraById(cathedraId, connection) != null){
            List<Student> studentsOfCurrentCathedra = new ArrayList<Student>(studentDao
                    .getAllStudentsWithCurrentCathedra(cathedraId, connection));
            for(Student s : studentsOfCurrentCathedra){
                System.out.println();
                System.out.println(s);
            }
            return studentsOfCurrentCathedra;
        }else {
            System.out.println("Cathedra with such id doesn't exists!");
            return null;
        }
    }
}
