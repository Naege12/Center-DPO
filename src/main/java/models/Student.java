package models;

public class Student extends Person {
    private int studentId;
    private String registerDate;

    public Student (String _registerDate, String _surname, String _name, String _patronymic, String _date, String _gender,  String _number, String _email)
    {
        super(_surname, _name, _patronymic, _date, _gender, _number, _email);
        this.studentId++;
        this.registerDate = _registerDate;
    }
    public void setRegisterDate(String newRegistetDate)
    {
        this.registerDate = newRegistetDate;
    }

    public String getRegisterDate()
    {
        return this.registerDate;
    }
}
