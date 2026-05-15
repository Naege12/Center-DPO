package models;

public class Teacher extends Person {
    private int teacherId;
    private String registerDate;
    private String specialization;

    public Teacher(String _registerDate, String _specialization, String _surname, String _name, String _patronymic, String _date, String _gender,  String _number, String _email)
    {
        super(_surname, _name, _patronymic, _date, _gender, _number, _email);
        this.teacherId++;
        this.registerDate = _registerDate;
        this.specialization = _specialization;
    }

    public void setRegisterDate(String _newRegisterDate)
    {
        this.registerDate = _newRegisterDate;
    }

    public void setSpecialization(String newSpecialization)
    {
        this.specialization = newSpecialization;
    }

    public String getRegisterDate()
    {
        return this.registerDate;
    }

    public String getSpecialization()
    {
        return specialization;
    }
}


