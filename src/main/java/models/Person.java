package models;

public class Person {
    protected String surname;
    protected String name;
    protected String patronymic;
    protected String date;
    protected String gender;
    protected String number;
    protected String email;

    public Person(String _surname, String _name, String _patronymic, String _date, String _gender,  String _number, String _email)
    {
       this.surname = _surname;
       this.name = _name;
       this.patronymic = _patronymic;
       this.date = _date;
       this.gender = _gender;
       this.number = _number;
       this.email = _email;
    }

    //Сеттеры
    public void setSurname(String newSurname)
    {
        this.surname = newSurname;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public void setPatronymic(String newPatrinymic)
    {
        this.patronymic = newPatrinymic;
    }

    public void setDate(String newDate)
    {
        this.date = newDate;
    }

    public void setGender(String newGender)
    {
        this.gender = newGender;
    }

    public void setNumber(String newNumber)
    {
        this.number = newNumber;
    }

    public void setEmail(String newEmail)
    {
        this.email = newEmail;
    }

    //Геттеры
    public String getSurname()
    {
       return this.surname;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPatronymic()
    {
        return this.patronymic;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getGender()
    {
        return this.gender;
    }

    public String getNumber()
    {
        return this.number;
    }

    public String getEmail()
    {
        return this.email;
    }

    //Методы
    public String getFullName()
    {
        return (surname + " " + name + " " + patronymic);
    }
}