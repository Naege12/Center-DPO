package models;

import java.time.LocalDate;
import java.util.ArrayList;


public class User extends Person {
    private String login;
    private String passvord;
    private LocalDate regDate;
    private String status;
    private String role;


    public User(String _surname, String _name, String _patronymic, String _date, String _gender, String _number, String _email, String login, String passvord, String status ,String role) {
        super(_surname, _name, _patronymic, _date, _gender, _number, _email);
        this.login = login;
        this.passvord = passvord;
        this.regDate = LocalDate.now();
        this.status = status;
        this.role = role;
    }
    
    public String getLogin()
    {
        return this.login;
    }
    
    public String getPassvord()
    {
        return this.passvord;
    }
    
    public String getRegDate()
    {
       return this.regDate.toString();
    }
    
    public String getStatus()
    {
        return this.status;
    }

    public String getRole()
    {
       return this.role;
    }

    public ArrayList<String> getFullInfo()
    {
        ArrayList<String> fullInfo = new ArrayList<>();


        fullInfo.add(getSurname());
        fullInfo.add(getName());
        fullInfo.add(getPatronymic());
        fullInfo.add(getDate());
        fullInfo.add(getGender());
        fullInfo.add(getNumber());
        fullInfo.add(getEmail());
        fullInfo.add(this.login);
        fullInfo.add(this.passvord);
        fullInfo.add(this.regDate.toString());
        fullInfo.add(this.status);
        fullInfo.add(this.role);

        return fullInfo;
    }

}
