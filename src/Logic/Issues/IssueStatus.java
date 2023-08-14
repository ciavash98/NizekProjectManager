package Logic.Issues;
public enum IssueStatus {

    TODO("Todo"),
    IN_PROGRESS("In Progress"),
    QA("Quality Assurance"),
    DONE("Done");

    String status;

    IssueStatus(String status) {
        this.status = status;
    }

    public static IssueStatus findByName(String name) {
        for (IssueStatus item : values()) {
            if(item.status.equals(name)){
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return status;
    }
}
