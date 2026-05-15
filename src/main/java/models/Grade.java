package models;

import java.time.LocalDate;

public class Grade {
    private Student student;
    private int grade;
    private LocalDate localDate;

    public Grade(Student student, int grade, LocalDate localDate) {
        this.student = student;
        this.grade = grade;
        this.localDate = localDate.now();
    }

    //Геттеры
    public Student getStudent() {
        return this.student;
    }

    public int getGrade() {
        return this.grade;
    }

    public LocalDate getLocalDate()
    {
        return this.localDate;
    }

    //Сеттеры
    public void setGrade(int newGrade)
    {
        this.grade = newGrade;
    }

    public void setStudent(Student newStudent)
    {
        this.student = newStudent;
    }

    public void setLocalDate(LocalDate newLocalDate)
    {
        this.localDate = newLocalDate;
    }
}

