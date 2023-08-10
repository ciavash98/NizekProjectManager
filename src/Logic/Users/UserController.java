package Logic.Users;

import java.util.ArrayList;
import java.util.HashMap;

public class UserController {

    public static User user;

    public UserError login(String email, String pass) {
        HashMap<Object, User> users = UserRepository.readUsers(UserInitKeyBy.EMAIL);
        User user = users.get(email);
        if (user != null && pass.equals(user.getPass())) {
            this.user = user;
            return null;
        } else {
            return UserError.WRONG_USER_OR_PASSWORD;
        }
    }

    public UserError signUp(String name, String email, String pass, String re_enterPass, Roles role){
        if (name.matches("^[a-zA-Z]+$")) {
            if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                if (pass.equals(re_enterPass)) {
                    HashMap<Object, User> users = UserRepository.readUsers(UserInitKeyBy.EMAIL);
                    int id = users.size();
                    User user = new User(id, name, email, pass, role);
                    UserRepository.addUser(user);
                    this.user = user;
                } else return UserError.PASSWORD_NOT_MATCH;
            }else return UserError.EMAIL_NOT_VALID;
        } else return UserError.NAME_NOT_VALID;
        return null;
    }

    public HashMap<Object, User> filter(Roles selectedRole) {
        HashMap<Object, User> users = UserRepository.readUsers(UserInitKeyBy.ID);

        int index = 0;
        HashMap<Object, User> filteredUser = new HashMap<>();
        for (User user : users.values()) {
            if (user.getRole() == selectedRole) {
                filteredUser.put(index, user);
                index++;
            }
        }
        return filteredUser;
    }

    public HashMap<Object, User> getAllUsers(UserInitKeyBy initKeyBy) {
        return UserRepository.readUsers(UserInitKeyBy.ID);
    }

    public void changePass(int id , String password) {
        HashMap<Object,User> usersList = getAllUsers(UserInitKeyBy.ID);

        for (User usr : usersList.values()) {
            if (usr.getId() == id){
                usersList.get(id).setPass(password);
            }
        }
        UserRepository.saveUsers(usersList);
    }

    public void deleteAccount(int id) {
        HashMap<Object,User> usersList = getAllUsers(UserInitKeyBy.ID);

        for (User usr : usersList.values()) {
            if (usr.getId() == id) {
                usersList.remove(id);
            }
        }
        UserRepository.saveUsers(usersList);
    }
}
