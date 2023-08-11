package UI.ManageUser;
import Logic.Users.User;
import Logic.Users.UserController;
import Logic.Users.UserInitKeyBy;
import Logic.Users.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassAndRemove extends JFrame {
    JPanel main = new JPanel();
    UserController userController;
    int selectedUser;
    User user;

    public ChangePassAndRemove(UserController userController, int selectedUser){
        this.userController = userController;
        this.selectedUser = selectedUser;
        user = userController.getAllUsers(UserInitKeyBy.ID).get(selectedUser);


        setTitle(user.getName());
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setLayout(null);
        setVisible(true);

        JLabel newPassword = new JLabel("Enter Password : ");
        newPassword.setBounds(60, 120, 200, 100);
        newPassword.setFont(new Font("X Roya", Font.BOLD, 15));
        main.add(newPassword);

        JTextField newPass = new JTextField();
        newPass.setBackground(new Color(255, 255, 255));
        newPass.setFont(new Font("X Traffic", Font.BOLD, 18));
        newPass.setBounds(220, 155, 250, 28);
        main.add(newPass);

        JLabel re_enterPassword = new JLabel("Re-enter password : ");
        re_enterPassword.setBounds(60, 190, 200, 100);
        re_enterPassword.setFont(new Font("X Roya", Font.BOLD, 15));
        main.add(re_enterPassword);

        JTextField re_enterPass = new JTextField();
        re_enterPass.setBackground(new Color(255, 255, 255));
        re_enterPass.setFont(new Font("X Traffic", Font.BOLD, 18));
        re_enterPass.setBounds(220, 220, 250, 28);
        main.add(re_enterPass);


        JButton changePass = new JButton("Submit");
        changePass.setBounds(57, 360, 500, 100);
        main.add(changePass);
        changePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String newPassword = newPass.getText();
                String re_enterPassword = re_enterPass.toString();
                if(!newPassword.equals(re_enterPassword)) {
                    userController.changePass(user.getId(), newPassword);
                    setVisible(false);
                } else {
                    System.out.println("not match");
                }
            }
        });

        JButton deleteAccount = new JButton("Delete Account");
        deleteAccount.setBounds(57, 500, 500, 35);
        main.add(deleteAccount);
        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userController.deleteAccount(user.getId());
                setVisible(false);
            }
        });
        add(main);
    }

}
