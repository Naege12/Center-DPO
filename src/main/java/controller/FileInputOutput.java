package controller;

import models.Student;
import models.Teacher;
import models.Program;
import java.io.*;
import java.util.ArrayList;


public class FileInputOutput {

    public static ArrayList<String> textFileRead(String path) throws IOException
    {
        BufferedReader filleIn = new BufferedReader(new FileReader(path));
        ArrayList<String> buffer = new ArrayList<>();
        String line;
        while ((line = filleIn.readLine()) != null)
        {
            buffer.add(line);
        }
        return buffer;
    }

    public static boolean textFileWrite(String path, ArrayList<String> buffer)
    {
        try(BufferedWriter fileOut = new BufferedWriter(new FileWriter(path, false)))
        {
            for (String line : buffer)
            {
                fileOut.write(line + '\n');
            }
            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    public static boolean studentsImport(ArrayList<String> sourse, ArrayList<Student> destination)
    {
        try
        {
            for(String line : sourse) {
                String[] parts = line.split(";");
                if (parts.length >= 7) {

                    String name = parts[0].trim();
                    String surname = parts[1].trim();
                    String patronymic = parts[2].trim();
                    String date = parts[3].trim();
                    String gender = parts[4].trim();
                    String registerDate = parts[5].trim();
                    String number = parts[6].trim();
                    String email = parts[7].trim();

                    Student newStudent;
                    newStudent = new Student(registerDate, surname, name, patronymic, date, gender, number, email);
                    destination.add(newStudent);
                    System.out.println("Студент успешно был добавлен!");
                }
            }
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Ошибка! Студенты небыли добавлены!" + e);
            return false;
        }
    }

    public static ArrayList<String> studentExport(ArrayList<Student> source) throws Exception
    {
        ArrayList<String> exportData = new ArrayList<String>();

        for(Student student : source)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(student.getName()).append(";");
            sb.append(student.getSurname()).append(";");
            sb.append(student.getPatronymic()).append(";");
            sb.append(student.getDate()).append(";");
            sb.append(student.getGender()).append(";");
            sb.append(student.getRegisterDate()).append(";");
            sb.append(student.getNumber()).append(";");
            sb.append(student.getEmail());
            exportData.add(sb.toString());
        }
        return exportData;
    }

    public static boolean teacherImport(ArrayList<String> sourse, ArrayList<Teacher> destination)
    {
        try
        {
            for(String line : sourse) {
                String[] parts = line.split(";");
                if (parts.length >= 9) {

                    String name = parts[0].trim();
                    String surname = parts[1].trim();
                    String patronymic = parts[2].trim();
                    String date = parts[3].trim();
                    String gender = parts[4].trim();
                    String registerDate = parts[5].trim();
                    String number = parts[6].trim();
                    String emaul = parts[7].trim();
                    String specialization = parts[8].trim();

                    Teacher newTeacher;
                    newTeacher = new Teacher(registerDate, specialization, surname, name, patronymic, date, gender, number, emaul);
                    destination.add(newTeacher);
                    System.out.println("Учитель успешно был добавлен!");
                }
            }

            return true;
        }
        catch (Exception e)
        {
            System.err.println("Ошибка! Учителя небыли добавлены!" + e);
            return false;
        }
    }

    public static ArrayList<String> teacherExport(ArrayList<Teacher> source) throws Exception
    {
        ArrayList<String> exportData = new ArrayList<String>();

        for(Teacher teacher : source)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(teacher.getName()).append(";");
            sb.append(teacher.getSurname()).append(";");
            sb.append(teacher.getPatronymic()).append(";");
            sb.append(teacher.getDate()).append(";");
            sb.append(teacher.getGender()).append(";");
            sb.append(teacher.getRegisterDate()).append(";");
            sb.append(teacher.getNumber()).append(";");
            sb.append(teacher.getEmail());
            sb.append(teacher.getSpecialization()).append(";");
            exportData.add(sb.toString());
        }
        return exportData;
    }

    public static boolean programsImport(ArrayList<String> sourse, ArrayList<Program> destination)
    {
        try
        {
            for(String line : sourse) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {

                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    String duration = parts[2].trim();
                    String category = parts[3].trim();

                    Program newProgram;
                    newProgram = new Program(name, description, duration, category);
                    destination.add(newProgram);
                    System.out.println("Программа была успешно добавлена!");
                }
            }

            return true;
        }
        catch (Exception e)
        {
            System.err.println("Ошибка! Программы небыли добавлены!" + e);
            return false;
        }
    }

    public static ArrayList<String> programExport(ArrayList<Program> source) throws Exception
    {
        ArrayList<String> exportData = new ArrayList<String>();

        for(Program program : source)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(program.getProgramName()).append(";");
            sb.append(program.getProgramDescription()).append(";");
            sb.append(program.getDuration()).append(";");
            sb.append(program.getCategory()).append(";");
            exportData.add(sb.toString());
        }
        return exportData;
    }
}