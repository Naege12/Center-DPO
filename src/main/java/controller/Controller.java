package controller;
import java.sql.*;
import java.time.LocalDate;


import models.User;

public class Controller {
    
    public boolean checkAccept (String login, String passvord)
    {
        return !(login.isEmpty() || passvord.isEmpty());
    }
    
    public boolean addNewUserCheckAccept(String loginString, String passvordString, String roleString, String surnameString, String nameString, String patronymicString) 
    {
        return !(loginString.isEmpty() || passvordString.isEmpty() || roleString.isEmpty() || surnameString.isEmpty() || nameString.isEmpty() || patronymicString.isEmpty());
    }
    
    public boolean addNewUserCheckNotNumber( String surnameString, String nameString, String patronymicString)
    {
        String[] blockedStringses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        for (String digit: blockedStringses) {
            if (surnameString.contains(digit) || nameString.contains(digit) || patronymicString.contains(digit)) {
                return false;
            }
        }
        return true;
    }

    public boolean addNewTeacherCheckAccept(String name, String patronymic, String surname, String number, String email, String specialization, String date, String gender)
    {
        return !(name.isEmpty() || patronymic.isEmpty() || surname.isEmpty() || number.isEmpty() || email.isEmpty() || specialization.isEmpty() || date.isEmpty() || gender.isEmpty());
    }

    public boolean addNewTeacherCheckNotNumber(String name, String patronymic, String surname, String specialization)
    {
        String[] blockedStringses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        for (String digit: blockedStringses) {
            if (name.contains(digit) || patronymic.contains(digit) || surname.contains(digit) || specialization.contains(digit)) {
                return false;
            }
        }
        return true;
    }

    public boolean addNewStudentCheckAccept(String name, String patronymic, String surname, String number, String email, String date, String gender)
    {
        return !(name.isEmpty() || patronymic.isEmpty() || surname.isEmpty() || number.isEmpty() || email.isEmpty() || date.isEmpty() || gender.isEmpty());
    }

    public boolean addNewStudentCheckNotNumber(String name, String patronymic, String surname)
    {
        String[] blockedStringses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        for (String digit: blockedStringses) {
            if (name.contains(digit) || patronymic.contains(digit) || surname.contains(digit)) {
                return false;
            }
        }
        return true;
    }

    public boolean addNewProgramCheckAccept(String name, String description, String duration, String category)
    {
        return !(name.isEmpty() || description.isEmpty() || duration.isEmpty() || category.isEmpty());
    }
    
    public int getAccept(String login, String passvord)
    {
        int role = 0;
        try(Connection con = ConnectionBD.connectionDB()) {
            String sql = "SELECT \"id_role\" FROM \"User\" WHERE \"Login\" = ? AND \"Passvord\" = ?";
            PreparedStatement prpQuery = con.prepareStatement(sql);
            prpQuery.setString(1, login);
            prpQuery.setString(2, passvord);
            ResultSet result = prpQuery.executeQuery();
            ResultSetMetaData rMetaData = result.getMetaData();
            int count = rMetaData.getColumnCount();
            if(count != 0)
            {
                while (result.next())
                {
                    role = result.getInt(1);
                }
            }
            return role;
        }
        catch(SQLException | ClassNotFoundException ex )
        {
            return 0;
        }
    }

    public boolean addNewUser(String login, String passvord, String role, String surname, String name, String patronymic, LocalDate ld) {
        try (Connection con = ConnectionBD.connectionDB())
        {
            String sql = "INSERT INTO \"User\"(\"Login\", \"Passvord\", \"id_role\", \"Surname\", \"Name\", \"Patronymic\", \"RegisterDate\") VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prpQuery = con.prepareStatement(sql);
            int id_role = Integer.parseInt(role);
            ld = LocalDate.now();

            prpQuery.setString(1, login);
            prpQuery.setString(2, passvord);
            prpQuery.setInt(3, id_role);
            prpQuery.setString(4, surname);
            prpQuery.setString(5, name);
            prpQuery.setString(6, patronymic);
            prpQuery.setDate(7, Date.valueOf(ld));

            int rowsAffected = prpQuery.executeUpdate();
            return rowsAffected > 0; // вернет true если запись добавлена
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Ошибка при добавлении пользователя: " + ex.getMessage());
            return false;
        }
    }

    public boolean addNewTeacher(String surname, String name, String patronymic, LocalDate date, String gender, String number, String email, String specialization) {
        try (Connection con = ConnectionBD.connectionDB())
        {
            String sql = "INSERT INTO \"Teacher\"(\"Surname\", \"Name\", \"Patronymic\", \"Date\", \"Gender\", \"Number\", \"Email\" ,\"RegisterDate\", \"Specialization\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prpQuery = con.prepareStatement(sql);
            LocalDate ld = LocalDate.now();

            prpQuery.setString(1, surname);
            prpQuery.setString(2, name);
            prpQuery.setString(3, patronymic);
            prpQuery.setDate(4, Date.valueOf(date));
            prpQuery.setString(5, gender);
            prpQuery.setString(6, number);
            prpQuery.setString(7, email);
            prpQuery.setString(9, specialization);
            prpQuery.setDate(8, Date.valueOf(ld));

            int rowsAffected = prpQuery.executeUpdate();
            return rowsAffected > 0; // вернет true если запись добавлена
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Ошибка при добавлении пользователя: " + ex.getMessage());
            return false;
        }
    }

    public boolean addNewStudent(String surname, String name, String patronymic, LocalDate date, String gender, String number, String email) {
        try (Connection con = ConnectionBD.connectionDB())
        {
            String sql = "INSERT INTO \"Student\"(\"Surname\", \"Name\", \"Patronymic\", \"Date\", \"Gender\", \"Number\", \"Email\" ,\"RegisterDate\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prpQuery = con.prepareStatement(sql);
            LocalDate ld = LocalDate.now();

            prpQuery.setString(1, surname);
            prpQuery.setString(2, name);
            prpQuery.setString(3, patronymic);
            prpQuery.setDate(4, Date.valueOf(date));
            prpQuery.setString(5, gender);
            prpQuery.setString(6, number);
            prpQuery.setString(7, email);
            prpQuery.setDate(8, Date.valueOf(ld));

            int rowsAffected = prpQuery.executeUpdate();
            return rowsAffected > 0; // вернет true если запись добавлена
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Ошибка при добавлении пользователя: " + ex.getMessage());
            return false;
        }
    }

    public boolean addNewProgram(String name, String description, String duration, String category) {
        try (Connection con = ConnectionBD.connectionDB()) {
            String sql = "INSERT INTO \"Program\"(\"Name\", \"Description\", \"Duration\", \"Category\") VALUES (?, ?, ?, ?)";
            PreparedStatement prpQuery = con.prepareStatement(sql);
            LocalDate ld = LocalDate.now();

            prpQuery.setString(1, name);
            prpQuery.setString(2, description);
            prpQuery.setString(3, duration);
            prpQuery.setString(4, category);


            int rowsAffected = prpQuery.executeUpdate();
            return rowsAffected > 0; // вернет true если запись добавлена
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Ошибка при добавлении программы: " + ex.getMessage());
            return false;
        }
    }

    public User getUserInDb(String login, String passvord)
    {
        try(Connection con = ConnectionBD.connectionDB()) {
            String sql = "SELECT * FROM \"User\" WHERE \"Login\" = ? AND \"Passvord\" = ?";
            PreparedStatement prpQuery = con.prepareStatement(sql);
            prpQuery.setString(1, login);
            prpQuery.setString(2, passvord);
            ResultSet result = prpQuery.executeQuery();
            ResultSetMetaData rMetaData = result.getMetaData();
            if (result.next()) {
                // Получаем данные из ResultSet
                String surname = result.getString("Surname");      // Предполагаемые имена колонок
                String name = result.getString("Name");
                String patronymic = result.getString("Patronymic");
                String date = result.getString("RegisterDate");
                String gender = "test";
                String number = "test";
                String email = "test";
                String userLogin = result.getString("Login");
                String userPassword = result.getString("Passvord");
                String status = result.getString("Status");
                String role = result.getString("id_role");

                // Создаём и возвращаем объект User
                return new User(
                        surname, name, patronymic, date, gender,
                        number, email, userLogin, userPassword,
                        status, role
                );
            }
            else
            {
                return null;
            }
        }
        catch(SQLException | ClassNotFoundException ex )
        {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean isDeleted(int id, String sql)
    {
        try(Connection con = ConnectionBD.connectionDB())
        {
            PreparedStatement prpQuery = con.prepareStatement(sql);

            prpQuery.setInt(1, id);
            int rowAffected = prpQuery.executeUpdate();

            return rowAffected > 0;

        }
        catch (SQLException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
