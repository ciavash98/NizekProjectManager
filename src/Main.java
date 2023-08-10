import Logic.Projects.Project;
import Logic.Projects.ProjectController;
import Logic.Users.*;
import UI.HomePage;
import UI.SignupPage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String[] args){
        UserController userController = new UserController();
        HashMap<Object, User> users = userController.getAllUsers(UserInitKeyBy.EMAIL);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                if (users.isEmpty()) {
                    new SignupPage();
                } else {
//                    new LoginPage();
                    User user = new User(1,"Ciavash", "Ciavash@yahoo.com", "123", Roles.SUPER_ADMIN);

                    userController.user = user;
                    try {
                        new HomePage(userController);
                        new ProjectController();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}