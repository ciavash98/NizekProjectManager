package Logic.Projects;
import java.util.ArrayList;
import java.util.Date;

public class Project {
    int id;
    String name;
    Date date;
    ArrayList<Integer> boardList;
    ArrayList<Integer> posList;
    ArrayList<Integer> qasList;

    public Project(int id, String name,ArrayList<Integer> boardList) {
        this.id = id;
        this.name = name;
        this.boardList = boardList;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Integer> getBoardList() {
        return boardList;
    }

    public void setBoardList(ArrayList<Integer> boardList) {
        this.boardList = boardList;
    }

    public ArrayList<Integer> getPosList() {
        return posList;
    }

    public void setPosList(ArrayList<Integer> posList) {
        this.posList = posList;
    }

    public ArrayList<Integer> getQasList() {
        return qasList;
    }

    public void setQasList(ArrayList<Integer> qasList) {
        this.qasList = qasList;
    }

    @Override
    public String toString() {
        return "Project:" +
                "id = " + id +
                " name = " + name ;
    }
}
