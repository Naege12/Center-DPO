package view;

import controller.ConnectionBD;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class StudentsForm extends JFrame {

    JFrame parent;
    public StudentsForm(JFrame jf)
    {
        initComponent();
        parent = jf;
    }

    private void initComponent()
    {
        setTitle("Окно просмотра курсов");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 700);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel menJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        menJPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        DefaultTableModel table = new DefaultTableModel();
        try(Connection con = ConnectionBD.connectionDB()) {
            String sql = "SELECT * FROM \"Student\" ";
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


        JPanel mainJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        mainJPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        mainJPanel.add(scrollPane);


        add(mainJPanel, BorderLayout.EAST);


        _table.setAutoCreateRowSorter(true);


        _table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        JPanel buttonJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton exitButton = new JButton("Выход");
        exitButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });
        buttonJPanel.add(exitButton);

        add(buttonJPanel, BorderLayout.WEST);
    }
}