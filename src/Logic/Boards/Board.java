package Logic.Boards;

import Logic.Issues.Issue;
import Logic.Projects.Project;

import java.util.ArrayList;

public class Board {

    private int id;
    private String name;
    ArrayList<Integer> issuesList;


    public Board(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
