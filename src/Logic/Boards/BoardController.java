package Logic.Boards;
import java.util.ArrayList;
public class BoardController {

    public ArrayList<Board> getAllBoards(){
       return BoardRepository.readBoards();
    }

    public void assign(int boardId, int issueId){
        ArrayList<Board> boards = getAllBoards();
        for (Board b : boards) {
            if(b.getId() == boardId){
                b.issuesList.add(issueId);
                boards.add(b);
            }
        }
        BoardRepository.saveBoards(boards);
    }

    public int addBoard(String name){
        int id = getAllBoards().size();
        Board board = new Board(id,name);
        BoardRepository.addBoard(board);
        return id;
    }

    public void renameBoard(String name){
        ArrayList<Board> boards = getAllBoards();
        for (Board b : boards) {
            if(b.getName().equals(name)){
                boards.remove(b);
                Board board = new Board(b.getId(),name);
                boards.add(board);
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

    public void removeBoard(int id) {
        ArrayList<Board> boardList = getAllBoards();

        for (Board board: boardList) {
            if(board.getId() == id){
                boardList.remove(id);
            }
        }
        BoardRepository.saveBoards(boardList);
    }
}
