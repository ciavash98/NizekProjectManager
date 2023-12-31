package Logic.Boards;
import Logic.Issues.Issue;
import Logic.Issues.IssueController;
import Logic.Projects.Project;
import Logic.Projects.ProjectController;
import java.util.ArrayList;
import java.util.Iterator;

public class BoardController {

    public ArrayList<Board> getAllBoards(){
       return BoardRepository.readBoards();
    }

    public int addBoard(String name){
        ArrayList<Board> boards = getAllBoards();
        int boardId = 0;
        if (boards.size() >= 1) {
            boardId = boards.get(boards.size() - 1).getId() + 1;
        }
        Board board = new Board(boardId, name);
        BoardRepository.addBoard(board);
        return boardId;
    }

    public void assignIssue(int boardId, int issueId) {
        ArrayList<Board> boards = getAllBoards();

        for (Board board : boards) {
            if (board.getId() == boardId) {
                board.getIssuesList().add(issueId);
            }
        }

        BoardRepository.saveBoards(boards);
    }

    public Board getBoardById(int id) {
        ArrayList<Board> boards = getAllBoards();
        for (Board board : boards) {
            if (board.getId() == id) {
                return board;
            }
        }
        return null;
    }

    public void removeBoardFromProject(int id) {
        ArrayList<Board> boardList = getAllBoards();
        Iterator<Board> boardIterator = boardList.iterator();

        while (boardIterator.hasNext()) {
            Board board = boardIterator.next();
            if (board.getId() == id) {
                boardIterator.remove();
                break;
            }
        }
        BoardRepository.saveBoards(boardList);
    }

    public void removeSingleBoard(int boardId) {
        ProjectController projectController = new ProjectController();
        IssueController issueController = new IssueController();
        ArrayList<Project> projects = projectController.getAllProjects();
        ArrayList<Board> boards = getAllBoards();
        for (Project project: projects) {
            if(project.getBoardList().contains(boardId)){
                project.getBoardList().remove(Integer.valueOf(boardId));
                projectController.saveProjects(projects);
            }
        }



        for (int i = 0; i < boards.size(); i++) {
            if(boards.get(i).getId() == boardId){
                ArrayList<Issue> allIssues = issueController.getAllIssues();
                ArrayList<Integer> issues = boards.get(i).getIssuesList();
                allIssues.removeIf(issue -> issues.contains(issue.getId()));
                issueController.saveIssue(allIssues);
                boards.remove(boards.get(i));
            }
        }
        BoardRepository.saveBoards(boards);
    }

    public void editBoardName(int id, String name) {
        ArrayList<Board> boards = getAllBoards();

        for (int i = 0; i < boards.size(); i++) {
            Board b = boards.get(i);

            if(b.getId() == id){
                Board editedBoard = new Board(id, name);
                editedBoard.setIssuesList(b.getIssuesList());
                boards.set(i, editedBoard);
            }
        }
        BoardRepository.saveBoards(boards);
    }

}
