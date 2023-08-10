package Logic.Issues;

import Logic.Users.Roles;

public enum IssuePriority {

    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    String title;

    IssuePriority(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public static IssuePriority findByName(String name) {
        for (IssuePriority item : values()) {
            if(item.title.equals(name)){
                return item;
            }
        }
        return null;
    }
}
