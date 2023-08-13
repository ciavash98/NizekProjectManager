package Logic.Issues;
import Logic.Projects.Project;
import Logic.Users.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IssueController {

    public ArrayList<Issue> getAllIssues(){
        return IssueRepository.readIssue();
    }

    public void addIssue(String title,IssueType type, String description, IssuePriority priority, Project project, User user){
        int id = getAllIssues().size();
        IssueStatus defaultStatus = IssueStatus.TODO;
        Issue issue = new Issue(id, title, defaultStatus, type, description, priority, user, project);
        issue.setAssignedUser(user);
        IssueRepository.addIssue(issue);
    }

    public void assign(Issue issue, User developer){
        ArrayList<Issue> issues = getAllIssues();
        for (Issue i: issues) {
            if(i.getId() == issue.getId()){
                i.setAssignedUser(developer);
            }
        }
        saveIssue(issues);
    }

    public void removeIssue(int id) {
        ArrayList<Issue> issueList = getAllIssues();

        for (Issue issue: issueList) {
            if(issue.getId() == id){
                issueList.remove(id);
            }
        }
        IssueRepository.saveIssue(issueList);
    }

    public Issue getIssueByTitle(String issueTitle){
        ArrayList<Issue> issues = getAllIssues();
        for (Issue issue: issues) {
            if (issue.getTitle().equals(issueTitle))
                return issue;
        }
        return null;
    }

    public int getIssueById(int id){
        ArrayList<Issue> issues = getAllIssues();
        for (Issue issue: issues) {
            if (issue.getId() == id) {
                return issue.getId();
            }
        }
        return -1;
    }

    public void saveIssue(ArrayList<Issue> issue){
        IssueRepository.saveIssue(issue);
    }

    public void editIssue(int issueId, String description){
        ArrayList<Issue> issues = getAllIssues();
        for (Issue i: issues) {
            if(i.getId() == issueId){
                i.setDescription(description);
                saveIssue(issues);
            }
        }
    }

    public void saveDoneIssues(){



    }
}
