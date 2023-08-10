package Logic.Issues;
import Logic.Projects.ProjectController;
import Logic.Users.User;
import Logic.Users.UserController;
import Logic.Users.UserInitKeyBy;
import Logic.Users.UserRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Scanner;

public class IssueRepository {

    public static void addIssue(Issue issue) {
        ArrayList<String > issueList = readIssuesFile("Issues.csv");

        issueList.add(issue.getId()
                + "," + issue.getTitle()
                + "," + issue.getIssueStatus()
                + "," + issue.getType()
                + "," + issue.getDescription()
                + "," + issue.getIssuePriority()
                + "," + issue.getAssignedUser().getId()
                + "," + issue.getProject().getId()
        );

        StringBuilder content = new StringBuilder();

        for (String string : issueList) {
            content.append(string);
            content.append("\n");
        }
        try {
            saveFile("Issues.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }
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

    private static ArrayList<String> readIssuesFile(String fileName) {
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

    public static ArrayList<Issue> readIssue() {
        ArrayList<Issue> issues = new ArrayList<>();
        ArrayList<String> issuesString = readIssuesFile("Issues.csv");
        UserController userController = new UserController();
        ProjectController projectController = new ProjectController();
        for (String data : issuesString) {
            String[] dataStringItem = data.split(",");
            Issue issue = new Issue(Integer.parseInt(dataStringItem[0]),
                    dataStringItem[1],
                    IssueStatus.findByName(dataStringItem[2]),
                    IssueType.findByName(dataStringItem[3]),
                    dataStringItem[4],
                    IssuePriority.findByName(dataStringItem[5]),
                    userController.getAllUsers(UserInitKeyBy.ID).get(Integer.parseInt(dataStringItem[6])),
                    projectController.findById(Integer.parseInt(dataStringItem[7])));
            issues.add(issue);
        }
        return issues;
    }

    public static void saveIssue(ArrayList<Issue> issues){
        StringBuilder content = new StringBuilder();

        for (Issue issue : issues) {

            content.append(issue.getId())
                    .append(",").append(issue.getTitle())
                    .append(",").append(issue.getIssueStatus())
                    .append(",").append(issue.getType())
                    .append(",").append(issue.getDescription())
                    .append(",").append(issue.getIssuePriority())
                    .append(",").append(issue.getAssignedUser().getId())
                    .append(",").append(issue.getProject().getId());

            content.append("\n");
        }
        try {
            saveFile("Issues.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }

    }

}
