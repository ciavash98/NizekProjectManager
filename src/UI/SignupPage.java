package UI;

import Logic.Users.Roles;
import Logic.Users.UserController;
import Logic.Users.UserError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class SignupPage extends JFrame {
    private final int cornerRadius = 60;
    class RoundedPanel extends JPanel {
        private Color startColor;
        private Color endColor;

        public RoundedPanel(Color startColor, Color endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int arcWidth = 80;
            int arcHeight = 80;

            int startX = 0;
            int startY = 0;
            int endX = getWidth();
            int endY = getHeight();

            GradientPaint gradientPaint = new GradientPaint(startX, startY, startColor, endX, endY, endColor);

            g2d.setPaint(gradientPaint);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        }
    }

    UserController userController = new UserController();
    JPanel welcomePanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int startX = 0;
            int startY = 0;
            int endX = getWidth();
            int endY = getHeight();

            Color startColor = new Color(189, 219, 218);
            Color endColor = new Color(140, 165, 186);

            GradientPaint gradientPaint = new GradientPaint(startX, startY, startColor, endX, endY, endColor);

            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    RoundedPanel borderPanel = new RoundedPanel(new Color(65, 90, 119),new Color(119, 141, 169)){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            setOpaque(false);
            int startX = 0;
            int startY = 0;
            int endX = getWidth();
            int endY = getHeight();
            int arcWidth = 80;
            int arcHeight = 80;

            Color startColor = new Color(65, 90, 119);
            Color endColor = new Color(119, 141, 169);
            RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

            GradientPaint gradientPaint = new GradientPaint(startX, startY, startColor, endX, endY, endColor);

            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    JPanel signupPanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            setOpaque(false);
            int startX = 0;
            int startY = 0;
            int endX = getWidth();
            int endY = getHeight();

            Color startColor = new Color(52, 78, 65);
            Color endColor = new Color(88, 129, 87);

            GradientPaint gradientPaint = new GradientPaint(startX, startY, startColor, endX, endY, endColor);

            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public SignupPage() {

        setTitle("NGI's Project Manager");
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomePanel.setLayout(null);
        welcomePanel.setBounds(150, 100, 800, 550);
        welcomePanel. setBorder(BorderFactory.createEmptyBorder(cornerRadius, cornerRadius, cornerRadius, cornerRadius));
        welcomePanel.setOpaque(false);
        borderPanel.add(welcomePanel);

        signupPanel.setLayout(null);
        signupPanel.setBounds((getWidth() / 2) - (330 / 2), 610, 330, 100);
        signupPanel.setOpaque(false);
        add(signupPanel);

        borderPanel.setLayout(null);
        borderPanel.setBounds(0, 0, 1100, 800);
        add(borderPanel);

        JLabel label = new JLabel("Welcome");
        label.setBounds((welcomePanel.getWidth() / 2) - (290 / 2), 50, 290, 100);
        label.setFont(new Font("X Roya", Font.BOLD, 80));
        welcomePanel.add(label);

        JLabel fullName = new JLabel("Full Name : ");
        fullName.setBounds(30, 240, 100, 100);
        fullName.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(fullName);

        JLabel email = new JLabel("Email(Username) : ");
        email.setBounds(30, 350, 200, 100);
        email.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(email);

        JLabel password = new JLabel("Password : ");
        password.setBounds(390, 240, 100, 100);
        password.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(password);

        JLabel re_EnterPassword = new JLabel("Re-Enter Password : ");
        re_EnterPassword.setBounds(390, 350, 200, 100);
        re_EnterPassword.setFont(new Font("X Roya", Font.BOLD, 15));
        welcomePanel.add(re_EnterPassword);

//        JLabel ifForgot = new JLabel("If forgot my password :");
//        ifForgot.setBounds(170, 485, 200, 100);
//        ifForgot.setFont(new Font("X Roya", Font.BOLD, 15));
//        welcomePanel.add(ifForgot);
//
//        JCheckBox sendSms = new JCheckBox("Send password by SMS");
//        sendSms.setBackground(Color.GRAY);
//        sendSms.setBounds(380, 487, 200, 10);
//        welcomePanel.add(sendSms);
//
//        JCheckBox sendEmail = new JCheckBox("Send password by Email");
//        sendEmail.setBackground(new Color(255, 239, 0));
//        sendEmail.setBounds(680, 487, 200, 10);
//        welcomePanel.add(sendEmail);

        JTextField name = new JTextField();
        name.setBackground(Color.GRAY);
        name.setFont(new Font("X Traffic", Font.BOLD, 18));
        name.setBounds(145, 276, 240, 28);
        welcomePanel.add(name);

        JTextField enterEmail = new JTextField();
        enterEmail.setBounds(145,387, 240, 28);
        enterEmail.setBackground(Color.GRAY);
        enterEmail.setFont(new Font("X Traffic", Font.BOLD, 18));
        welcomePanel.add(enterEmail);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(520, 276, 240, 28);
        pass.setBackground(Color.GRAY);
        pass.setFont(new Font("X Traffic", Font.BOLD, 18));
        welcomePanel.add(pass);

        JPasswordField rePass = new JPasswordField();
        rePass.setBounds(520, 387, 240, 28);
        rePass.setBackground(Color.GRAY);
        rePass.setFont(new Font("X Traffic", Font.BOLD, 18));
        welcomePanel.add(rePass);

        JLabel validation = new JLabel();
        validation.setBounds((welcomePanel.getWidth() / 2) - (300 / 2), 575, 300, 100);
        validation.setForeground(Color.RED);
        validation.setFont(new Font("X Roya", Font.BOLD, 17));
        validation.setHorizontalAlignment(SwingConstants.CENTER);
        welcomePanel.add(validation);


        JButton signupButton = new JButton("SignUp!");
        signupButton.setBounds((signupPanel.getWidth() / 2) - (300 / 2), (signupPanel.getHeight() / 2) - (80 / 2), 300, 80);
        signupPanel.add(signupButton);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = name.getText();
                String email = enterEmail.getText();
                String password = pass.getText();
                String rePassword = rePass.getText();
                UserError userError = userController.signUp(fullName, email, password, rePassword, Roles.SUPER_ADMIN);

                if (userError != null) {
                    switch (userError) {
                        case NAME_NOT_VALID -> validation.setText("Name should be A-Z.");
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

