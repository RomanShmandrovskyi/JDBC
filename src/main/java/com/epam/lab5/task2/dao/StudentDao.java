package com.epam.lab5.task2.dao;

import com.epam.lab5.task2.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    public void add(int addressId, int cathedraId, Student student, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO student(student_name, student_surname, " +
                "student_gender, student_birth_day, " +
                "student_phone, student_exam_book_number, " +
                "student_start_day, student_study_form, " +
                "student_course, student_address, cathedra_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1, student.getStudentName());
        preparedStatement.setString(2, student.getStudentSurname());
        preparedStatement.setString(3, student.getStudentGender());
        preparedStatement.setString(4, student.getStudentBirthDay());
        preparedStatement.setString(5, student.getStudentPhone());
        preparedStatement.setInt(6, student.getExamBookNumber());
        preparedStatement.setString(7, student.getStartDay());
        preparedStatement.setString(8, student.getStudyForm());
        preparedStatement.setInt(9, student.getStudentCourse());
        preparedStatement.setInt(10, addressId);
        preparedStatement.setInt(11, cathedraId);

        preparedStatement.execute();
    }

    public Student findStudentById(int id, Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE student_id = ?");
        preparedStatement.setInt(1, id);

        rs = preparedStatement.executeQuery();

        if(rs.next()){
            return new Student(rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("student_surname"),
                    rs.getString("student_gender"),
                    rs.getString("student_birth_day"),
                    rs.getString("student_phone"),
                    rs.getInt("student_exam_book_number"),
                    rs.getString("student_start_day"),
                    rs.getString("student_study_form"),
                    rs.getInt("student_course"),
                    rs.getInt("student_address"),
                    rs.getInt("cathedra_id"));
        }else {
            return null;
        }
    }

    public void edit(Student student, Connection connection) {

    }

    public void delete(int id, Connection connection){

    }

    public List<Student> getAllStudents(Connection connection) throws SQLException {
        List<Student> students = new ArrayList<Student>();
        preparedStatement = connection.prepareStatement("SELECT * FROM student");
        rs = preparedStatement.executeQuery();

        while (rs.next()){
            students.add(new Student(rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("student_surname"),
                    rs.getString("student_gender"),
                    rs.getString("student_birth_day"),
                    rs.getString("student_phone"),
                    rs.getInt("student_exam_book_number"),
                    rs.getString("student_start_day"),
                    rs.getString("student_study_form"),
                    rs.getInt("student_course"),
                    rs.getInt("student_address"),
                    rs.getInt("cathedra_id")));
        }
        return students;
    }

    public List<Student> getAllStudentsWithCurrentCathedra(int cathedraId, Connection connection) throws SQLException {
        List<Student> studentOfCurrentCathedra = new ArrayList<Student>();

        preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE cathedra_id = ?");
        preparedStatement.setInt(1, cathedraId);

        rs = preparedStatement.executeQuery();

        while(rs.next()){
            studentOfCurrentCathedra.add(new Student(rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("student_surname"),
                    rs.getString("student_gender"),
                    rs.getString("student_birth_day"),
                    rs.getString("student_phone"),
                    rs.getInt("student_exam_book_number"),
                    rs.getString("student_start_day"),
                    rs.getString("student_study_form"),
                    rs.getInt("student_course"),
                    rs.getInt("student_address"),
                    rs.getInt("cathedra_id")));
        }

        return studentOfCurrentCathedra;
    }

    public List<Student> findStudentsWithSameAddress(int addressId, Connection connection) throws SQLException {
        List<Student> studentList = new ArrayList<Student>();
        preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE student_address = ?");
        preparedStatement.setInt(1, addressId);

        rs = preparedStatement.executeQuery();

        while (rs.next()){
            studentList.add(new Student(rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("student_surname"),
                    rs.getString("student_gender"),
                    rs.getString("student_birth_day"),
                    rs.getString("student_phone"),
                    rs.getInt("student_exam_book_number"),
                    rs.getString("student_start_day"),
                    rs.getString("student_study_form"),
                    rs.getInt("student_course"),
                    rs.getInt("student_address"),
                    rs.getInt("cathedra_id")));
        }

        return studentList;
    }

    public void updateStudentAddressId(List<Student> studentList, int id, Connection connection) throws SQLException {
        for(Student s : studentList){
            preparedStatement = connection.prepareStatement("UPDATE student SET student_address = ? WHERE student_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, s.getStudentId());
            preparedStatement.executeUpdate();
        }
    }

    public void setMessage(String message){
        System.out.println(message);
    }
}
