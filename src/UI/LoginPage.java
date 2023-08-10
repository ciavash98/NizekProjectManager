package UI;

import Logic.Users.UserController;
import Logic.Users.UserError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LoginPage extends JFrame {

    UserController userController = new UserController();

    public LoginPage(){

        setTitle("NGI's Project Manager");
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(255, 255, 255));
        loginPanel.setBounds(0, 0, 1100, 200);
        add(loginPanel);

        JLabel email = new JLabel("Email: ");
        email.setBounds(340, 380, 200, 100);
        email.setFont(new Font("X Roya", Font.BOLD, 20));
        loginPanel.add(email);

        JTextField enterEmail = new JTextField();
        enterEmail.setBounds(480,415, 240, 28);
        enterEmail.setBackground(new Color(255, 255, 255));
        enterEmail.setFont(new Font("X Traffic", Font.BOLD, 18));
        loginPanel.add(enterEmail);

        JLabel password = new JLabel("Password : ");
        password.setBounds(340, 455, 100, 100);
        password.setFont(new Font("X Roya", Font.BOLD, 20));
        loginPanel.add(password);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(480, 493, 240, 28);
        pass.setBackground(new Color(255, 255, 255));
        pass.setFont(new Font("X Traffic", Font.BOLD, 18));
        loginPanel.add(pass);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds((loginPanel.getWidth() / 2) - (300 / 2), 600, 300, 80);
        loginPanel.add(loginButton);

        InputMap inputMap = loginButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = loginButton.getActionMap();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, "selectButton");
        actionMap.put("selectButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });

        JLabel validation = new JLabel();
        validation.setBounds((loginPanel.getWidth() / 2) - (300 / 2), 520, 300, 100);
        validation.setForeground(Color.RED);
        validation.setFont(new Font("X Roya", Font.BOLD, 17));
        validation.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(validation);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = enterEmail.getText();
                String password = pass.getText();
                UserError userError = userController.login(email, password);

                if (userError != null) {
                    switch (userError) {
                        case WRONG_USER_OR_PASSWORD -> validation.setText("Email or password is wrong");
                    }
                } else {
                    setVisible(false);
                    try {
                        new HomePage(userController);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        setVisible(true);
    }

}
