package UI.ManageUser;
import Logic.Users.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AddUser extends JFrame {
    UserController userController = new UserController();
    public AddUser() {

        setTitle("Add New User");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        JLabel fullName = new JLabel("Full Name : ");
        fullName.setBounds(30, 270, 100, 100);
        fullName.setFont(new Font("Poppins", Font.BOLD, 13));
        add(fullName);

        JLabel email = new JLabel("Email(Username) : ");
        email.setBounds(30, 380, 200, 100);
        email.setFont(new Font("Poppins", Font.BOLD, 13));
        add(email);

        JLabel password = new JLabel("Password : ");
        password.setBounds(400, 270, 100, 100);
        password.setFont(new Font("Poppins", Font.BOLD, 13));
        add(password);

        JLabel re_EnterPassword = new JLabel("Re-Enter Password : ");
        re_EnterPassword.setBounds(400, 380, 200, 100);
        re_EnterPassword.setFont(new Font("Poppins", Font.BOLD, 13));
        add(re_EnterPassword);

        JLabel ifForgot = new JLabel("If i forgot my password :");
        ifForgot.setBounds(30, 485, 200, 100);
        ifForgot.setFont(new Font("Poppins", Font.BOLD, 13));
        add(ifForgot);

        JCheckBox sendSms = new JCheckBox("Send password by SMS");
        sendSms.setBackground(new Color(255, 255, 255));
        sendSms.setFont(new Font("Poppins", Font.BOLD, 13));
        sendSms.setBounds(200, 527, 200, 13);
        add(sendSms);

        JTextField name = new JTextField();
        name.setBackground(new Color(255, 255, 255));
        name.setFont(new Font("Poppins", Font.BOLD, 18));
        name.setBounds(155, 306, 240, 28);
        add(name);

        JTextField enterEmail = new JTextField();
        enterEmail.setBounds(155, 417, 240, 28);
        enterEmail.setBackground(new Color(255, 255, 255));
        enterEmail.setFont(new Font("Poppins", Font.BOLD, 18));
        add(enterEmail);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(530, 306, 240, 28);
        pass.setBackground(new Color(255, 255, 255));
        pass.setFont(new Font("Poppins", Font.BOLD, 18));
        add(pass);

        JPasswordField rePass = new JPasswordField();
        rePass.setBounds(530, 417, 240, 28);
        rePass.setBackground(new Color(255, 255, 255));
        rePass.setFont(new Font("Poppins", Font.BOLD, 18));
        add(rePass);

        JLabel validation = new JLabel();
        validation.setBounds(250, 550, 300, 100);
        validation.setForeground(Color.RED);
        validation.setFont(new Font("Poppins", Font.BOLD, 15));
        validation.setHorizontalAlignment(SwingConstants.CENTER);
        add(validation);

        JLabel selectRole = new JLabel("Role : ");
        selectRole.setBounds(400, 523, 200, 25);
        selectRole.setFont(new Font("Poppins", Font.BOLD, 13));
        add(selectRole);

        JComboBox<Roles> roleComboBox = new JComboBox<>(Roles.values());
        selectRole.setFont(new Font("Poppins", Font.BOLD, 13));
        roleComboBox.setBounds(460, 525, 315, 25);
        roleComboBox.setVisible(true);
        add(roleComboBox);

        JButton addUserButton = new JButton("Add User");
        addUserButton.setFont(new Font("Poppins", Font.BOLD, 13));
        addUserButton.setBounds((getWidth() / 2) - (300 / 2), 620, 300, 80);
        add(addUserButton);

        InputMap inputMap = addUserButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = addUserButton.getActionMap();
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, "selectButton");
        actionMap.put("selectButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserButton.doClick();
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = name.getText();
                String email = enterEmail.getText();
                String password = pass.getText();
                String rePassword = rePass.getText();
                Roles selectedRole = Roles.findByName(roleComboBox.getSelectedItem().toString());
                UserError userError = userController.signUp(fullName, email, password, rePassword, selectedRole);
                if (userError != null) {
                    switch (userError) {
                        case NAME_NOT_VALID -> validation.setText("Fullname should be A-Z.");
                        case EMAIL_NOT_VALID -> validation.setText("Enter a valid Email address.");
                        case PASSWORD_NOT_MATCH -> validation.setText("Passwords dose not match.");
                        case EMAIL_DOSE_ALREADY_REGISTERED -> validation.setText("This email already registered");
                    }
                } else {
                    setVisible(false);
                }
            }
        });
    }
}
