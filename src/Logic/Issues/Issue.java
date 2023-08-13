package Logic.Issues;
import Logic.Boards.Board;
import Logic.Projects.Project;
import Logic.Users.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Issue {

    int id;
    String title;
    IssueStatus issueStatus;
    String description;
    IssuePriority issuePriority;
    IssueType type;
    Project project;
    Board board;
    ArrayList<String> todo;
    ArrayList<String> inProgress;
    ArrayList<String> qa;
    ArrayList<String> done;
    User assignedUser;
    Date date;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public Issue(int id, String title, IssueStatus status, IssueType type, String description, IssuePriority issuePriority, User assignedUser, Project project) {
        this.id = id;
        this.title = title;
        this.issueStatus = status;
        this.type = type;
        this.description = description;
        this.issuePriority = issuePriority;
        this.project = project;
        this.assignedUser = assignedUser;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<String> getTodo() {
        return todo;
    }

    public void setTodo(ArrayList<String> todo) {
        this.todo = todo;
    }

    public ArrayList<String> getInProgress() {
        return inProgress;
    }

    public void setInProgress(ArrayList<String> inProgress) {
        this.inProgress = inProgress;
    }

    public ArrayList<String> getQa() {
        return qa;
    }

    public void setQa(ArrayList<String> qa) {
        this.qa = qa;
    }

    public ArrayList<String> getDone() {
        return done;
    }

    public void setDone(ArrayList<String> done) {
        this.done = done;
    }


    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssuePriority getIssuePriority() {
        return issuePriority;
    }

    public void setIssuePriority(IssuePriority issuePriority) {
        this.issuePriority = issuePriority;
    }


    @Override
    public String toString() {
        return title;
    }
}
