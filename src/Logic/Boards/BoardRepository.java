package Logic.Boards;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardRepository {

    public static void addBoard(Board board) {
        ArrayList<String> boardList = readBoardFile("Boards.csv");
        boardList.add(board.getId() + (",") + board.getName());
        StringBuilder content = new StringBuilder();

        for (String string : boardList) {
            content.append(string);
            content.append("\n");
        }

        try {
            saveFile("Boards.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveBoards(ArrayList<Board> boards){
        StringBuilder content = new StringBuilder();

        for (Board board : boards) {
            content.append(board.getId()).append(",").append(board.getName());
            content.append("\n");
        }

        try {
            saveFile("Boards.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }

    }

    public static void saveFile(String fileName, String content) throws FileSystemException {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new FileSystemException("Failed to save file: " + fileName);
        }
    }

    private static ArrayList<String> readBoardFile(String fileName) {
        ArrayList<String> stringUsers = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                stringUsers.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found!");
        }
        return stringUsers;
    }

    public static ArrayList<Board> readBoards() {
            ArrayList<Board> boards = new ArrayList<>();
            ArrayList<String> boardsString = readBoardFile("Boards.csv");
            for (String data : boardsString) {
                String[] dataStringItem = data.split(",");
                Board board = new Board(Integer.parseInt(dataStringItem[0]),
                        dataStringItem[1]
                );
                boards.add(board);
            }
         return boards;
    }

}
