package Logic.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;


public class UserRepository {

    public static void addUser(User user) {
        ArrayList<String> usersList = readUsersFile("Users.csv");
        usersList.add(user.getId()+","+user.getName()+","+user.getEmail()+","+user.getPass()+","+user.getRole().toString());
        StringBuilder content = new StringBuilder();

        for (String string : usersList) {
            content.append(string);
            content.append("\n");
        }

        try {
            saveFile("Users.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<Object, User> readUsers(UserInitKeyBy initDictKeyBy) {
        HashMap<Object, User> users = new HashMap<>();
        if (initDictKeyBy == UserInitKeyBy.ID) {
            ArrayList<String> usersString = readUsersFile("Users.csv");
            for (String data : usersString) {
                String[] dataStringItem = data.split(",");
                User user = new User(Integer.parseInt(dataStringItem[0]),
                        dataStringItem[1],
                        dataStringItem[2],
                        dataStringItem[3],
                        Roles.findByName(dataStringItem[4])
                );
                users.put(user.getId(), user);
            }
        } else if (initDictKeyBy == UserInitKeyBy.EMAIL) {
            ArrayList<String> usersString = readUsersFile("Users.csv");
            for (String data : usersString) {
                String[] dataStringItem = data.split(",");
                User user = new User(Integer.parseInt(dataStringItem[0]),
                        dataStringItem[1],
                        dataStringItem[2],
                        dataStringItem[3],
                        Roles.findByName(dataStringItem[4])
                );
                users.put(user.getEmail(), user);
            }
        }
        return users;
    }

    public static void saveFile(String fileName, String content) throws FileSystemException {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new FileSystemException("Failed to save file: " + fileName);
        }
    }

    public static void saveUsers(HashMap<Object, User> users){
        StringBuilder content = new StringBuilder();

        for (User user : users.values()) {
            content.append(user.getId()).append(",").append(user.getName()).append(",").append(user.getEmail()).append(",").append(user.getPass()).append(",").append(user.getRole().toString());
            content.append("\n");
        }

        try {
            saveFile("Users.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }

    }

    // Always should be private
    private static ArrayList<String> readUsersFile(String fileName) {
        ArrayList<String> stringUsers = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                stringUsers.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found!");
        }
        return stringUsers;
    }
    public static User findByName(String name) {
        HashMap<Object ,User> users = readUsers(UserInitKeyBy.ID);
        for (User user: users.values()) {
            if (user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

}
