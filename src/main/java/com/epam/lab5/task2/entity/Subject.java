package com.epam.lab5.task2.entity;

public class Subject {
    private int subjectId;
    private String subjectName;
    private String subjectDescription;

    //foreign key
    private int cathedraId;
    private Cathedra cathedra;

    public Subject() {
    }

    public Subject(int subjectId, String subjectName, String subjectDescription, int cathedraId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.cathedraId = cathedraId;
    }



    public Subject(String subjectName, String subjectDescription, Cathedra cathedra) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.cathedra = cathedra;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public int getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(int cathedraId) {
        this.cathedraId = cathedraId;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }

    @Override
    public String toString() {
        return "\tsubject id: " + subjectId +
                "\n\tname: " + subjectName +
                "\n\tdescription: " + subjectDescription +
                "\n\tcathedra id: " + cathedraId;
    }
}
