package com.epam.lab5.task2.dao;

import com.epam.lab5.task2.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    public void add(int cathedraId, Subject subject, Connection connection)
            throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO subject(subject_name, subject_description, cathedra_id) VALUES (?,?,?)");

        preparedStatement.setString(1, subject.getSubjectName());
        preparedStatement.setString(2, subject.getSubjectDescription());
        preparedStatement.setInt(3, cathedraId);

        preparedStatement.execute();
    }

    public Subject findSubjectById(int id, Connection connection)
            throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM subject WHERE subject_id = ?");
        preparedStatement.setInt(1, id);
        rs = preparedStatement.executeQuery();

        if (rs.next()){
            return new Subject(rs.getInt("subject_id"),
                    rs.getString("subject_name"),
                    rs.getString("subject_description"),
                    rs.getInt("cathedra_id"));
        }else {
            return null;
        }
    }

    public void edit(Subject subject, Connection connection)
            throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE subject SET subject_name = ?, subject_description = ?");
        preparedStatement.setString(1, subject.getSubjectName());
        preparedStatement.setString(2, subject.getSubjectDescription());

        preparedStatement.executeUpdate();
    }

    public void delete(int id, Connection connection)
            throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM subject WHERE subject_id = ?");

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Subject> getAllSubjects(Connection connection)
            throws SQLException {
        List<Subject> subjectList = new ArrayList<Subject>();

        preparedStatement = connection.prepareStatement("SELECT * FROM subject");
        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            subjectList.add(new Subject(rs.getInt("subject_id"),
                    rs.getString("subject_name"),
                    rs.getString("subject_description"),
                    rs.getInt("cathedra_id")));
        }
        return subjectList;
    }

    public List<Subject> getAllSubjectsOfCurrentCathedra(int cathedraId, Connection connection)
            throws SQLException {
        List<Subject> subjectListOfCurrentCathedra = new ArrayList<Subject>();
        preparedStatement = connection.prepareStatement("SELECT * FROM subject WHERE cathedra_id = ?");
        preparedStatement.setInt(1, cathedraId);
        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            subjectListOfCurrentCathedra.add(new Subject(rs.getInt("subject_id"),
                    rs.getString("subject_name"),
                    rs.getString("subject_description"),
                    rs.getInt("cathedra_id")));
        }

        return subjectListOfCurrentCathedra;
    }
}
