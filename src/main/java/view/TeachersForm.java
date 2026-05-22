package view;

import controller.ConnectionBD;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TeachersForm extends JFrame {

    private JFrame parrentForm;
    public TeachersForm(JFrame parrentForm)
    {
        this.parrentForm = parrentForm;
        InitComponent();
    }

    void InitComponent()
    {
        setTitle("Учителя");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 700);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        DefaultTableModel table = new DefaultTableModel();
        try(Connection con = ConnectionBD.connectionDB()) {
            String sql = "SELECT \"Surname\" AS Фамилия, \"Name\" as Имя, \"Patronymic\" as Отчество, \"Date\" as \"Дата рождения\", \"Gender\" as Пол, \"Number\" AS \"Номер телефона\", \"Email\" as Почта, \"RegisterDate\" AS \"Дата регистрации\", \"Specialization\" as Специализация From \"Teacher\" ";
            PreparedStatement prpQuery = con.prepareStatement(sql);
            ResultSet result = prpQuery.executeQuery();
            ResultSetMetaData rMetaData = result.getMetaData();
            int count = rMetaData.getColumnCount();
            for (int i = 1; i <= count; i++)
            {
                table.addColumn(rMetaData.getColumnLabel(i));
            }

            while (result.next()) {
                Object[] row = new Object[count];
                for (int i = 1; i <= count; i++) {
                    row[i - 1] = result.getObject(i);

                    if (result.wasNull()) {
                        row[i - 1] = "Н/Д";
                    }
                }
                table.addRow(row);
            }

        }
        catch(SQLException | ClassNotFoundException ex )
        {
            ex.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Ошибка загрузки данных: " + ex.getMessage(),
                    "Ошибка",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }

        JTable _table = new JTable(table);


        JScrollPane scrollPane = new JScrollPane(_table);

        mainPanel.add(scrollPane);

        JPanel buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new BoxLayout(buttonJPanel, BoxLayout.Y_AXIS));
        buttonJPanel.setBorder(new EmptyBorder(80, 20, 90, 20));

        String[]  nameButton = {"Добавить", "Удалить", "Изменить данные", "Назад"};
        int count = 0;
        for (String item : nameButton) {
            JButton menuButton = new JButton(item);
            menuButton.setSize(80, 30);
            menuButton.setFont(new Font("Arial", Font.PLAIN, 14));
            if (count == 0)
                menuButton.addActionListener(e -> {
                    AddTeacherForm teacherForm = new AddTeacherForm(this);
                    teacherForm.setVisible(true);
                    this.dispose();
                });

            if (count == 3)
                menuButton.addActionListener(e -> {
                    parrentForm.setVisible(true);
                    this.dispose();
                });
            buttonJPanel.add(menuButton);
            buttonJPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            count++;
        }


        mainPanel.add(buttonJPanel);



        add(mainPanel, BorderLayout.CENTER);
    }

}
