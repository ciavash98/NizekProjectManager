package UI.Reports;
import Logic.Issues.Issue;
import Logic.Issues.IssueRepository;
import Logic.Issues.IssueStatus;
import Logic.Users.Roles;
import Logic.Users.User;
import Logic.Users.UserController;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportController {

    UserController userController = new UserController();

    public HashMap<Object,User> getDevelopers(){
        return userController.filter(Roles.DEVELOPER);
    }

    public ArrayList<Issue> getAllIssues(){
        return IssueRepository.readIssue();
    }

    public ArrayList<Issue> findIssueByStatus(IssueStatus issueStatus){
        ArrayList<Issue> issues = getAllIssues();
        ArrayList<Issue> filteredIssues = new ArrayList<>();
        for (Issue issue : issues) {
            if(issue.getIssueStatus() == issueStatus){
                filteredIssues.add(issue);
            }
        }
        return filteredIssues;
    }

    public int userDoneIssues(int userId){
        ArrayList<Issue> doneIssue = findIssueByStatus(IssueStatus.DONE);
        ArrayList<Issue> userIssues = new ArrayList<>();

        for (Issue issue : doneIssue) {
            if(issue.getAssignedUser().getId() == userId){
                userIssues.add(issue);
            }
        }
        return userIssues.size();
    }

    public int userRejectedIssues(int userId){
        ArrayList<Issue> issues = getAllIssues();
        ArrayList<Issue> rejectedIssues = new ArrayList<>();
        for (Issue issue: issues) {
            if(issue.isARejected()){
                if (issue.getId() == userId) {
                    rejectedIssues.add(issue);
                }
            }
        }
        System.out.println(rejectedIssues);
        return rejectedIssues.size();
    }
}
