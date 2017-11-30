package com.epam.lab5.task2.entity;

import java.util.List;

public class Student {
    private int studentId;
    private String studentName;
    private String studentSurname;
    private String studentGender;
    private String studentBirthDay;
    private String studentPhone;
    private int examBookNumber;
    private String startDay;
    private String studyForm;
    private int studentCourse;

    //foreign keys
    private Address studentAddress;
    private int studentAddressId;

    private Cathedra cathedra;
    private int cathedraId;

    //List of subjects
    private List<Subject> subjectList;

    public Student() {
    }

    public Student(String studentName, String studentSurname, String studentGender,
                   String studentBirthDay, String studentPhone, int examBookNumber,
                   String startDay, String studyForm, int studentCourse,
                   Address studentAddress, Cathedra cathedra) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentGender = studentGender;
        this.studentBirthDay = studentBirthDay;
        this.studentPhone = studentPhone;
        this.examBookNumber = examBookNumber;
        this.startDay = startDay;
        this.studyForm = studyForm;
        this.studentCourse = studentCourse;
        this.studentAddress = studentAddress;
        this.cathedra = cathedra;
    }

    public Student(int studentId, String studentName, String studentSurname,
                   String studentGender, String studentBirthDay, String studentPhone,
                   int examBookNumber, String startDay, String studyForm,
                   int studentCourse, int studentAddressId, int cathedraId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentGender = studentGender;
        this.studentBirthDay = studentBirthDay;
        this.studentPhone = studentPhone;
        this.examBookNumber = examBookNumber;
        this.startDay = startDay;
        this.studyForm = studyForm;
        this.studentCourse = studentCourse;
        this.studentAddressId = studentAddressId;
        this.cathedraId = cathedraId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentBirthDay() {
        return studentBirthDay;
    }

    public void setStudentBirthDay(String studentBirthDay) {
        this.studentBirthDay = studentBirthDay;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public int getExamBookNumber() {
        return examBookNumber;
    }

    public void setExamBookNumber(int examBookNumber) {
        this.examBookNumber = examBookNumber;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(String studyForm) {
        this.studyForm = studyForm;
    }

    public int getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(int studentCourse) {
        this.studentCourse = studentCourse;
    }

    public Address getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(Address studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public int getStudentAddressId() {
        return studentAddressId;
    }

    public void setStudentAddressId(int studentAddressId) {
        this.studentAddressId = studentAddressId;
    }

    public int getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(int cathedraId) {
        this.cathedraId = cathedraId;
    }

    @Override
    public String toString() {
        return "\tstudent id: " + studentId +
                "\n\tname: " + studentName +
                "\n\tsurname: " + studentSurname +
                "\n\tgender: " + studentGender +
                "\n\tbirthDay: " + studentBirthDay +
                "\n\tphone: '" + studentPhone +
                "\n\texam book number: " + examBookNumber +
                "\n\tstart day: " + startDay +
                "\n\tstudy form: '" + studyForm +
                "\n\tcourse: " + studentCourse;
    }
}
