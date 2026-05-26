package centerdpo;

import controller.ConnectionBD;
import javax.swing.JOptionPane;
import view.LoginForm;
import javax.swing.SwingUtilities;

public class CenterDPOStrokatov {
    public static void main(String[] args) throws ClassNotFoundException {
        if (!ConnectionBD.isConnectDB()) {
            JOptionPane.showMessageDialog(null, "Ошибка подключения к бд! Завершение работы", "ОШИБКА", 0);
            System.exit(0);
        }

        LoginForm loginForm = new LoginForm();
        SwingUtilities.invokeLater(() -> {
            loginForm.setVisible(true);
        });

    }
}