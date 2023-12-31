package Logic.Issues;
import Logic.Boards.Board;
import Logic.Projects.Project;
import Logic.Users.User;
import java.util.ArrayList;
import java.util.Date;

public class IssueController {

    public ArrayList<Issue> getAllIssues(){
        ArrayList<Issue> issues = IssueRepository.readIssue();
        ArrayList<Issue> filteredIssues = new ArrayList<>();

        for (Issue issue : issues) {
            if (issue.getIssueStatus() == IssueStatus.DONE) {
                if (!isOutDate(issue.getDate())) {
                    filteredIssues.add(issue);
                }
            } else {
                filteredIssues.add(issue);
            }
        }

        return filteredIssues;
    }

    private boolean isOutDate(Date givenDate) {
        Date currentDate = new Date();

        long differenceInMillis = currentDate.getTime() - givenDate.getTime();

        // Convert milliseconds to days
        int daysDifference = (int) (differenceInMillis / (24 * 60 * 60 * 1000));

//        Convert milliseconds to minutes
//        long minutesDifference = timeDifferenceInMillis / (60 * 1000);
//        if (minutesDifference >= 1) {
//            return true;
//        } else {
//            return false;
//        }

        if (daysDifference >= 30) {
            return true;
        } else {
            return false;
        }
    }

    public int addIssue(String title, IssueType type, String description, IssuePriority priority, User user){
        int id = getAllIssues().size();
        IssueStatus defaultStatus = IssueStatus.TODO;
        Issue issue = new Issue(id, title, defaultStatus, type, description, priority, user);
        issue.setAssignedUser(user);
        IssueRepository.addIssue(issue);
        return id;
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

    public Issue getProjectIssue(int issueId) {
        ArrayList<Issue> issues = getAllIssues();
        for(Issue issue : issues) {
            if (issue.getId() == issueId) {
                return issue;
            }
        }

        return null;
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

}
