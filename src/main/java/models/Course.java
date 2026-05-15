package models;

import models.Student;
import models.Teacher;
import models.Grade;
import models.Program;
import java.time.LocalDate;
import java.util.ArrayList;

public class Course {
    private int coursId;
    private Program program;
    private String startDate;
    private String endDate;
    private Teacher teacher;
    private ArrayList<Student> students;
    private ArrayList<Grade> gradeBook;
    private int maxStudents;
    private LocalDate lc;

    public Course(Program program, String startDate, String enddate, Teacher teacher, int maxStudents) {
        coursId++;
        this.program = program;
        this.startDate = startDate;
        this.endDate = enddate;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
        students = new ArrayList<>();
        gradeBook = new ArrayList<>();
    }

    //Геттеры

    public String getProgram() {
        return this.program.toString();
    }

    public String getStartDate()
    {
        return this.startDate;
    }

    public String getEndDate()
    {
        return this.endDate;
    }
    

    public String getTeacher()
    {
        return this.teacher.toString();
    }

    public int getMaxtudents()
    {
        return this.maxStudents;
    }

    //Сеттеры
    public void setProgram(Program _newProgram)
    {
        this.program = _newProgram;
    }

    public void setStartDate(String newStartDate)
    {
        this.startDate = newStartDate;
    }

    public void setEndDate(String newEndDate)
    {
        this.endDate = newEndDate;
    }

    public void setTeacher(Teacher newTeacher)
    {
        this.teacher = newTeacher;
    }

    public void setMaxStudents(int newMaxStudents)
    {
        this.maxStudents = newMaxStudents;
    }

    public void setGradeBook (ArrayList<Grade> newGradeBook)
    {
        this.gradeBook.addAll(newGradeBook);
    }

    //Методы

    public void addStudent(Student newStudent)
    {
        students.add(newStudent);
    }

    public void deleteStudent(Student student)
    {
        students.remove(student);
    }

    public void setTeacherAddCourse(Teacher teacher)
    {
        this.teacher = teacher;
    }

    public boolean putGrade(Student student, int grade)
    {
        try
        {
            Grade newGrade = new Grade(student, grade, lc.now());
            this.gradeBook.add(newGrade);
            System.out.println("Оценка - " + grade + "была выставленна студенту - " + student.getFullName());
            return true;

        }
        catch (Exception e)
        {
            System.err.println("Ошибка ! Оценка не была выставленна!" + e);
            return false;
        }
    }
}

