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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static Logic.Projects.ProjectRepository.parseDate;

public class IssueRepository {

    public static void addIssue(Issue issue) {
        ArrayList<String > issueList = readIssuesFile("Issues.csv");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        issueList.add(issue.getId()
                + "," + issue.getTitle()
                + "," + issue.getIssueStatus()
                + "," + issue.isARejected()
                + "," + issue.getType()
                + "," + issue.getDescription()
                + "," + issue.getIssuePriority()
                + "," + issue.getAssignedUser().getId()
                + "," + issue.getProject().getId()
                + "," + dateFormatter.format(issue.getDate())
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
                    IssueType.findByName(dataStringItem[4]),
                    dataStringItem[5],
                    IssuePriority.findByName(dataStringItem[6]),
                    userController.getAllUsers(UserInitKeyBy.ID).get(Integer.parseInt(dataStringItem[7])),
                    projectController.findById(Integer.parseInt(dataStringItem[8])));
            issue.setDate(parseDate(dataStringItem[9]));
            issue.setARejected(Boolean.parseBoolean(dataStringItem[3]));
            issues.add(issue);
        }
        return issues;
    }

    public static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + dateString);
        }
    }

    public static Date completionDate(Issue issue){

        if(issue.getIssueStatus().equals(IssueStatus.DONE))
            return issue.getDate();
                else return null;
    }

    public static void saveIssue(ArrayList<Issue> issues){
        StringBuilder content = new StringBuilder();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Issue issue : issues) {

            content.append(issue.getId())
                    .append(",").append(issue.getTitle())
                    .append(",").append(issue.getIssueStatus())
                    .append(",").append(issue.isARejected())
                    .append(",").append(issue.getType())
                    .append(",").append(issue.getDescription())
                    .append(",").append(issue.getIssuePriority())
                    .append(",").append(issue.getAssignedUser().getId())
                    .append(",").append(issue.getProject().getId())
                    .append(",").append(dateFormatter.format(issue.getDate()));

            content.append("\n");
        }
        try {
            saveFile("Issues.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }

    }

}
