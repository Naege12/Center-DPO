package view;

import controller.Controller;
import javax.swing.JOptionPane;
import models.User;


public class LoginForm extends javax.swing.JFrame {


    public LoginForm() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        passvordJLable = new javax.swing.JLabel();
        loginJlable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        passvordJTextPane = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        loginJTextPane = new javax.swing.JTextPane();
        loginJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setName("LoginForm"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Авторизация в системе");

        passvordJLable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        passvordJLable.setText("Пароль:");

        loginJlable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        loginJlable.setText("Логин:");

        jScrollPane1.setViewportView(passvordJTextPane);

        jScrollPane2.setViewportView(loginJTextPane);

        loginJButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        loginJButton.setText("Вход");
        loginJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passvordJLable, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginJlable, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(loginJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(191, 191, 191))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginJlable))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passvordJLable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(loginJButton)))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginJButtonActionPerformed

        Controller con = new Controller();
        String login = loginJTextPane.getText();
        String passvord = passvordJTextPane.getText();
        
        if(!con.checkAccept(login, passvord))
        {
            JOptionPane.showMessageDialog(this, "Пользователь не найден или незаполнены все поля!");
        }
        else
        {
           System.out.println(login + passvord);
           User user = con.getUserInDb(login, passvord);
                switch(con.getAccept(login, passvord))
                {
                    case 1 -> {
                        JOptionPane.showMessageDialog(this, "Добро пожаловать Эндминистратор!");
                        passvordJTextPane.setText("");
                        FormSysAdmin adminForm = new FormSysAdmin(user, this);
                        adminForm.setVisible(true);
                        this.dispose();
                    }
                    case 2 -> {
                        JOptionPane.showMessageDialog(this, "Добро пожаловать гость!");
                        passvordJTextPane.setText("");
                        GuestForm guestForm = new GuestForm(user, this);
                        guestForm.setVisible(true);
                        this.dispose();
                    }
                    default -> {
                        JOptionPane.showMessageDialog(this, "Обнаружена попытка нелегального входа!");
                        loginJTextPane.setText("");
                        passvordJTextPane.setText("");
                        
                    }
                }
        }
    }//GEN-LAST:event_loginJButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loginJButton;
    private javax.swing.JTextPane loginJTextPane;
    private javax.swing.JLabel loginJlable;
    private javax.swing.JLabel passvordJLable;
    private javax.swing.JTextPane passvordJTextPane;
    // End of variables declaration//GEN-END:variables
}
