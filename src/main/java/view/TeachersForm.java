package view;

import controller.ConnectionBD;
import controller.JTableXmlExporter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TeachersForm extends JFrame {

    private JFrame parrentForm;
    private JTable _table;
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
            String sql = "SELECT \"Surname\" AS surname, " +
                    "\"Name\" AS name, " +
                    "\"Patronymic\" AS patronymic, " +
                    "\"Date\" AS birth_date, " +
                    "\"Gender\" AS gender, " +
                    "\"Number\" AS phone, " +
                    "\"Email\" AS email, " +
                    "\"RegisterDate\" AS register_date, " +
                    "\"Specialization\" AS specialization " +
                    "FROM \"Teacher\"";
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

        _table = new JTable(table);


        JScrollPane scrollPane = new JScrollPane(_table);

        mainPanel.add(scrollPane);

        JPanel buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new BoxLayout(buttonJPanel, BoxLayout.Y_AXIS));
        buttonJPanel.setBorder(new EmptyBorder(80, 20, 90, 20));

        String[]  nameButton = {"Экспорт в XML", "Добавить", "Удалить", "Назад"};
        int count = 0;
        for (String item : nameButton) {
            JButton menuButton = new JButton(item);
            menuButton.setSize(80, 30);
            menuButton.setFont(new Font("Arial", Font.PLAIN, 14));
            if (count == 0) {
                menuButton.addActionListener(e -> exportToXml());
            }

            if (count == 1)
                menuButton.addActionListener(e -> {
                    AddTeacherForm teacherForm = new AddTeacherForm(this);
                    teacherForm.setVisible(true);
                    this.dispose();
                });

            if (count == 2)
            {
                menuButton.addActionListener(e -> {
                    DeleteTeacherForm delete = new DeleteTeacherForm(this);
                    delete.setVisible(true);
                    this.dispose();
                });
            }

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

    private void exportToXml() {
        if (_table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Нет данных для экспорта",
                    "Информация",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }


        JTableXmlExporter.exportAll(
                _table,                    // ваша таблица
                "teachers",                // корневой тег
                "teacher",                 // тег одной записи
                "teachers_export.xml"      // имя файла
        );

        JOptionPane.showMessageDialog(this,
                "Экспорт завершён!\nФайл: teachers_export.xml",
                "Успех",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
