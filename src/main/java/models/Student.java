package models;

import controller.XmlExportable;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student extends Person implements XmlExportable {
    private String registerDate;

    public Student (String _registerDate, String _surname, String _name, String _patronymic, String _date, String _gender,  String _number, String _email)
    {
        super(_surname, _name, _patronymic, _date, _gender, _number, _email);
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


    public String getXmlTagName()
    {
        return "студент";
    }

    public Map<String, String> getXmlAttributes()
    {
        Map<String, String> attrs = new LinkedHashMap<>();
        attrs.put("registerDate", registerDate);
        return attrs;
    }

    public Map<String, String> getXmlElements()
    {
        Map<String, String> elements = new LinkedHashMap<>();


        elements.put("surname", getSurname());
        elements.put("name", getName());
        elements.put("patronymic", getPatronymic());
        elements.put("birthDate", getDate());
        elements.put("gender", getGender());
        elements.put("phone", getNumber());
        elements.put("email", getEmail());

        return elements;
    }


}
