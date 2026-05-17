package view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;
import models.User;


public class FormSysAdmin extends JFrame {

    ArrayList<String> userInfoArr;
    JFrame parrentForm;
    public FormSysAdmin(User user, JFrame parrentForm)
    {
        this.parrentForm = parrentForm;
        userInfoArr = user.getFullInfo();
        System.out.println(userInfoArr);
        initComponents();
    }


    public void initComponents() {

        int count = 0;
        setTitle("Окно администратора системы");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 250);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        ArrayList<JLabel> labels;
        labels = new ArrayList<>();
        String[] nameLables = {"Имя пользователя", "Роль", "Дата/Время входа"};
        for (String itemString : nameLables) {
            JLabel label = new JLabel(itemString);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            labels.add(label);
        }
        labels.get(0).setText("Пользователь: " + userInfoArr.get(0) + " " + userInfoArr.get(1) + " " + userInfoArr.get(2));
        userInfoPanel.add(labels.get(0));

        String role_id = userInfoArr.get(11);
        switch (role_id){
            case "1":
                labels.get(1).setText("Роль: " + "Админ");
                break;
            case "2":
                labels.get(1).setText("Роль: " + "Гость");
                break;
        }
        userInfoPanel.add(labels.get(1));


        LocalDate lc = LocalDate.now();
        labels.get(2).setText("Дата/Время входа: " + lc.toString());
        userInfoPanel.add(labels.get(2));
        add(userInfoPanel, BorderLayout.NORTH);

        JPanel menJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        menJPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        String[] menuItems = {"Преподователи", "Студенты", "Программы", "Курсы", "Выход"};
        count = 0;
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setSize(80, 30);
            menuButton.setFont(new Font("Arial", Font.PLAIN, 14));
            if (count == 4)
                menuButton.addActionListener(e -> {
                    parrentForm.setVisible(true);
                    this.dispose();
                });
            if (count == 3)
                menuButton.addActionListener(e -> {
                    CourseForm course = new CourseForm(this);
                    course.setVisible(true);
                    this.dispose();
                });
            if (count == 1)
                menuButton.addActionListener(e -> {
                    StudentsForm studentsForm = new StudentsForm(this);
                    studentsForm.setVisible(true);
                    this.dispose();
                });
            if (count == 0)
                menuButton.addActionListener( e -> {
                    TeachersForm teacher = new TeachersForm(this);
                    teacher.setVisible(true);
                    this.dispose();
                });
            menJPanel.add(menuButton);
            count++;
        }

        add(menJPanel);

        JPanel tableJPanel = new JPanel(new BorderLayout(10, 10));
        tableJPanel.setBorder(BorderFactory.createTitledBorder("Пользователи"));

        JPanel buttonPanell = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton userAddButton = new JButton("Добавить пользователя");
        userAddButton.setFont(new Font("Arial", Font.BOLD, 14));

        userAddButton.addActionListener(e -> {
            FormUserAdd formUserAdd = new FormUserAdd(this);
            formUserAdd.setVisible(true);
            this.setVisible(false);
        });
        JButton userDeleteButton = new JButton("Удалить пользователя");
        userDeleteButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanell.add(userAddButton);
        buttonPanell.add(userDeleteButton);
        tableJPanel.add(buttonPanell, BorderLayout.NORTH);

        add(tableJPanel);
        setVisible(true);
    }

}