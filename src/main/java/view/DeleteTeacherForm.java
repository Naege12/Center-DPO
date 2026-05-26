package view;

import controller.ConnectionBD;
import controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DeleteTeacherForm extends JFrame
{
    private JTextField idField;
    private JFrame parrentForm;
    private JTable _table;

    public DeleteTeacherForm(JFrame parrentForm)
    {
        this.parrentForm = parrentForm;
        initComponent();
    }

    private void initComponent()
    {
        setTitle("Удаление учителя");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        DefaultTableModel table = new DefaultTableModel();
        try(Connection con = ConnectionBD.connectionDB()) {
            String sql = "SELECT \"id\" AS ID, " +
                    "\"Surname\" AS surname, " +
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

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(80, 20, 90, 20));

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(idLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        idField = new JTextField(15);
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(idField);

        mainPanel.add(inputPanel);

        JPanel buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new BoxLayout(buttonJPanel, BoxLayout.Y_AXIS));
        buttonJPanel.setBorder(new EmptyBorder(80, 20, 90, 20));

        String[]  nameButton = {"Удалить", "Назад"};  // поменял "Применить" на "Удалить"
        int count = 0;
        for (String item : nameButton) {
            JButton menuButton = new JButton(item);
            menuButton.setSize(80, 30);
            menuButton.setFont(new Font("Arial", Font.PLAIN, 14));

            if (count == 1)
                menuButton.addActionListener(e -> {
                    parrentForm.setVisible(true);
                    this.dispose();
                });


            if (count == 0) {
                menuButton.addActionListener(e -> {
                    String id = idField.getText();
                    if (!id.isEmpty()) {
                        delete(id);
                        idField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Введите ID учителя",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                                idField.setText("");
                    }
                });
            }


            buttonJPanel.add(menuButton);
            buttonJPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            count++;
        }

        mainPanel.add(buttonJPanel);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void delete(String _id)
    {
        int id = Integer.parseInt(_id);
        String sql = "DELETE FROM \"Teacher\" WHERE \"id\" = ?";
        Controller con = new Controller();
        if (con.isDeleted(id, sql))
        {
            JOptionPane.showMessageDialog(this, "Преподователь успешно удален");
        }
    }
}
