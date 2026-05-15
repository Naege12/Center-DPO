package view;
import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.border.EmptyBorder;


public class FormUserAdd extends JFrame {
    
    JFrame parrentFrame;
    public FormUserAdd(JFrame jf)
    {
        initComponents();
        parrentFrame = jf;
    }
    
    public void initComponents()
    {
        LocalDate ld = LocalDate.now();
        setTitle("Создание пользователя");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        
        setLocationRelativeTo(null);
        
        setResizable(false);
        
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JPanel lablePanel = new JPanel();
        lablePanel.setLayout(new BoxLayout(lablePanel, BoxLayout.Y_AXIS));
        lablePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        String[] nameLable = {"Логин", "Пароль", "Роль", "Фамилия", "Имя", "Отчество"};
        for(String name : nameLable){
            JLabel label = new JLabel(name);
            label.setSize(80,30);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setBorder(new EmptyBorder(10,20,10,20));
            lablePanel.add(label);
        }
        add(lablePanel, BorderLayout.EAST);
        
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
        textFieldPanel.setBorder(new EmptyBorder(10, 20,10,20));

        JTextField loginJTextField = new JTextField();
        loginJTextField.setPreferredSize(new Dimension (80, 30));
        loginJTextField.setFont(new Font("Arial", Font.BOLD, 14));
        textFieldPanel.add(loginJTextField);
        
        JTextField passvordJTextField = new JTextField();
        passvordJTextField.setPreferredSize(new Dimension (80, 30));
        passvordJTextField.setFont(new Font("Arial", Font.BOLD, 14));
        textFieldPanel.add(passvordJTextField);
        
        JTextField roleJTextField = new JTextField();
        roleJTextField.setPreferredSize(new Dimension (80, 30));
        roleJTextField.setFont(new Font("Arial", Font.BOLD, 14));
        textFieldPanel.add(roleJTextField);
        
        JTextField surnameJTextField = new JTextField();
        surnameJTextField.setPreferredSize(new Dimension (80, 30));
        surnameJTextField.setFont(new Font("Arial", Font.BOLD, 14));
        textFieldPanel.add(surnameJTextField);
        
        JTextField nameJTextField = new JTextField();
        nameJTextField.setPreferredSize(new Dimension (80, 30));
        nameJTextField.setFont(new Font("Arial", Font.BOLD, 14));
        textFieldPanel.add(nameJTextField);
        
        JTextField patronymicJTextField = new JTextField();
        patronymicJTextField.setPreferredSize(new Dimension (80, 30));
        patronymicJTextField.setFont(new Font("Arial", Font.BOLD, 14));
        textFieldPanel.add(patronymicJTextField);
        add(textFieldPanel, BorderLayout.CENTER);
        
        JPanel buttonJPanel = new JPanel(); 
        buttonJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonJPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        
        JButton addUserButton = new JButton("Добавить");
        addUserButton.addActionListener(e -> {
            String loginString = loginJTextField.getText();
            String passvordString = passvordJTextField.getText();
            String roleString = roleJTextField.getText();
            String surnameString = surnameJTextField.getText();
            String nameString = nameJTextField.getText();
            String patronymicString = patronymicJTextField.getText();
            Controller con = new Controller();
            if (! con.addNewUserCheckAccept(loginString, passvordString, roleString, surnameString, nameString, patronymicString)) {
                JOptionPane.showMessageDialog(this ,"Заполните все поля!");
            }
            else if(!con.addNewUserCheckNotNumber(surnameString, nameString, patronymicString)) {
                JOptionPane.showMessageDialog(this, "Обнаружены цифры в полях: роль, фамилия, имя, отчество!");
                nameJTextField.setText("");
                surnameJTextField.setText("");
                patronymicJTextField.setText("");
            }
            else 
            {
                if(con.addNewUser(loginString, passvordString, roleString, surnameString, nameString, patronymicString, ld)) {

                    JOptionPane.showMessageDialog(this, "Новый пользователь успешно добавлен!");
                    loginJTextField.setText("");
                    passvordJTextField.setText("");
                    roleJTextField.setText("");
                    surnameJTextField.setText("");
                    nameJTextField.setText("");
                    patronymicJTextField.setText("");
                }
            }
        });
        JButton backButton = new JButton("Выход");
        backButton.addActionListener(e -> {
           parrentFrame.setVisible(true);
           this.dispose();
        });
        
        buttonJPanel.add(addUserButton);
        buttonJPanel.add(backButton);
        
        add(buttonJPanel, BorderLayout.WEST);
    } 
}