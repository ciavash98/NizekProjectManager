package UI;

import Logic.Users.Roles;
import Logic.Users.UserController;
import Logic.Users.UserError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignupPage extends JFrame {

    UserController userController = new UserController();

    public SignupPage() {


        setTitle("NGI's Project Manager");
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBounds(0, 0, 1100, 200);
        add(welcomePanel);

//        JPanel infoPanel = new JPanel();
//        infoPanel.setLayout(null);
//        infoPanel.setBackground(new Color(100, 100, 145));
//        infoPanel.setBounds(0, 300, 1100, 300);
//        add(infoPanel, BorderLayout.SOUTH);

        JLabel label = new JLabel("Welcome");
        label.setBounds((welcomePanel.getWidth() / 2) - (290 / 2), 50, 290, 100);
        label.setFont(new Font("X Roya", Font.BOLD, 80));
        welcomePanel.add(label);

        JLabel fullName = new JLabel("Full Name : ");
        fullName.setBounds(170, 270, 100, 100);
        fullName.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(fullName);

        JLabel email = new JLabel("Email(Username) : ");
        email.setBounds(170, 380, 200, 100);
        email.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(email);

        JLabel password = new JLabel("Password : ");
        password.setBounds(530, 270, 100, 100);
        password.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(password);

        JLabel re_EnterPassword = new JLabel("Re-Enter Password : ");
        re_EnterPassword.setBounds(530, 380, 200, 100);
        re_EnterPassword.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(re_EnterPassword);

        JLabel ifForgot = new JLabel("If i forgot my password :");
        ifForgot.setBounds(170, 485, 200, 100);
        ifForgot.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(ifForgot);

        JCheckBox sendSms = new JCheckBox("Send password by SMS");
        sendSms.setBackground(Color.GRAY);
        sendSms.setBounds(380, 487, 200, 10);
        welcomePanel.add(sendSms);

        JCheckBox sendEmail = new JCheckBox("Send password by Email");
        sendEmail.setBackground(new Color(255, 239, 0));
        sendEmail.setBounds(680, 487, 200, 10);
        welcomePanel.add(sendEmail);

        JTextField name = new JTextField();
        name.setBackground(Color.GRAY);
        name.setFont(new Font("X Traffic", Font.BOLD, 18));
        name.setBounds(285, 306, 240, 28);
        welcomePanel.add(name);

        JTextField enterEmail = new JTextField();
        enterEmail.setBounds(285,417, 240, 28);
        enterEmail.setBackground(Color.GRAY);
        enterEmail.setFont(new Font("X Traffic", Font.BOLD, 18));
        welcomePanel.add(enterEmail);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(660, 306, 240, 28);
        pass.setBackground(Color.GRAY);
        pass.setFont(new Font("X Traffic", Font.BOLD, 18));
        welcomePanel.add(pass);

        JPasswordField rePass = new JPasswordField();
        rePass.setBounds(660, 417, 240, 28);
        rePass.setBackground(Color.GRAY);
        rePass.setFont(new Font("X Traffic", Font.BOLD, 18));
        welcomePanel.add(rePass);

        JButton loginButton = new JButton("SignUp!");
        loginButton.setBounds((welcomePanel.getWidth() / 2) - (300 / 2), 620, 300, 80);
        welcomePanel.add(loginButton);

        JLabel validation = new JLabel();
        validation.setBounds((welcomePanel.getWidth() / 2) - (300 / 2), 575, 300, 100);
        validation.setForeground(Color.RED);
        validation.setFont(new Font("X Roya", Font.BOLD, 17));
        validation.setHorizontalAlignment(SwingConstants.CENTER);
        welcomePanel.add(validation);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = name.getText();
                String email = enterEmail.getText();
                String password = pass.getText();
                String rePassword = rePass.getText();
                UserError userError = userController.signUp(fullName, email, password, rePassword, Roles.SUPER_ADMIN);

                if (userError != null) {
                    switch (userError) {
                        case NAME_NOT_VALID -> validation.setText("Fullname should be A-Z.");
                        case EMAIL_NOT_VALID -> validation.setText("Enter a valid Email address.");
                        case PASSWORD_NOT_MATCH -> validation.setText("Passwords dose not match.");
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
        }
        );
        setVisible(true);
    }
}
