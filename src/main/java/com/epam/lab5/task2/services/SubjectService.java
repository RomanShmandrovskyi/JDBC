package com.epam.lab5.task2.services;

import com.epam.lab5.task2.dao.CathedraDao;
import com.epam.lab5.task2.dao.SubjectDao;
import com.epam.lab5.task2.entity.Cathedra;
import com.epam.lab5.task2.entity.Subject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectService {
    private static SubjectDao subjectDao = new SubjectDao();
    private static CathedraDao cathedraDao = new CathedraDao();
    private Scanner scanner = new Scanner(System.in);

    //додати новий предмет
    public Subject add(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter subject name: ");
        String name = sc.nextLine();

        System.out.print("Enter subject description: ");
        String description = sc.nextLine();

        //обирання кафедри для предмету
        System.out.println("Choose cathedra for this subject from existing: ");

        //перелік доступних кафедр
        for (Cathedra c : cathedraDao.getAllCathedras(connection)) {
            System.out.println(String.format("\t%s - %s", c.getCathedraId(), c.getCathedraName()));
        }
        System.out.print("Chose cathedra: ");
        int cathedraId = sc.nextInt();
        //пошук кафедри з введеним ід
        Cathedra cathedra = cathedraDao.findCathedraById(cathedraId, connection);
        //створення об'єкту subject
        Subject subject = new Subject(name, description, cathedra);
        //додавання об'єкту subject до БД
        subjectDao.add(cathedra.getCathedraId(), subject, connection);

        System.out.println("Subject added successfully!");

        return subject;
    }

    //знайти предмет по введеному ід
    public Subject findSubjectById(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter subject id: ");
        while (!sc.hasNextInt()) {//отримання предмету по ід
            sc.next();
            System.out.print("Invalid input! Try again: ");//чек на невірний ввід
        }
        int subjectId = sc.nextInt();//введення ід
        Subject subject = subjectDao.findSubjectById(subjectId, connection);//пошук предмету з введеним ід

        if (subject != null) {//якщо предмет існує - виводж ну екран
            System.out.println(subject);
        } else { //якщо не існує - помилка
            System.out.println("Subject with such id doesn't exist");
        }
        return subject;
    }

    //апдейт предмета
    public void edit(Connection connection)
            throws SQLException {
        Subject subject;
        int choice, subjectId;

        while (true) {
            System.out.print("Enter subject id to update: ");
            while(!scanner.hasNextInt()){//отримання кафедри по ід
                scanner.next();
                System.out.print("Invalid input! Try again: ");//чек на невірний ввід
            }
            subjectId = scanner.nextInt();
            subject = subjectDao.findSubjectById(subjectId, connection); //старий об'єкт
            if(subject != null){//якщо знайшло об'єкт
                System.out.println("Old subject info: ");
                System.out.println(subject);//виводить не екран
                break;//вихід з циклу
            }else {//інакше - помилка
                System.out.println("Subject with such id doesn't exist");
            }
        }

        while (true) {//меню апдейту
            System.out.print("Enter:\n1 - to update subject name" +
                    "\n2 - to update subject description" +
                    "\n3 - to save" +
                    "\nYour choice: ");
            choice = scanner.nextInt();
            Scanner sc = new Scanner(System.in);
            if (choice == 1) {
                System.out.print("Enter new name: ");
                String s = sc.nextLine();
                subject.setSubjectName(s);
            } else if (choice == 2) {
                System.out.print("Enter new description: ");
                String s = sc.nextLine();
                subject.setSubjectDescription(s);
            } else {
                break;
            }
            System.out.println("OK!\n");
        }

        subjectDao.edit(subject, connection);//апдейт предмету

        System.out.println("Saved!");

        System.out.println(subject);//вивід нового предмету на екран
    }

    public void delete(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter subject id to delete: ");
        int id = scanner.nextInt();
        subjectDao.delete(id, connection);
    }

    public List<Subject> getAllSubjects(Connection connection) throws SQLException {
        return subjectDao.getAllSubjects(connection);
    }

    public List<Subject> getAlSubjectOfCurrentCathedra(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter cathedra id, to get subjects: ");
        int cathedraId = sc.nextInt();
        if (cathedraDao.findCathedraById(cathedraId, connection) != null) {
            List<Subject> subjectList = new ArrayList<Subject>(subjectDao
                    .getAllSubjectsOfCurrentCathedra(cathedraId, connection));

            for (Subject s : subjectList) {
                System.out.println(s);
                System.out.println();
            }
            return subjectList;
        } else {
            System.out.println("There is no one cathedra with such id");
            return null;
        }
    }
}
