package Logic.Issues;

public enum IssueType {

    STORY("Story"),
    TASK("Task"),
    BUG("Bug");

    String type;

    IssueType(String title) {
        this.type = title;
    }

    public String getTitle() {
        return type;
    }

    public void setTitle(String title) {
        this.type = title;
    }


    public static IssueType findByName(String name) {
        for (IssueType item : values()) {
            if(item.type.equals(name)){
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return type;
    }
}
