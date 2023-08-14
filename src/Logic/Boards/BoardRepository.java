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
        boardList.add(board.getId() + "," + board.getName()  + "," + listToString(board.issuesList));
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

    private static ArrayList<Integer> stringToList(String str) {
        ArrayList<Integer> list = new ArrayList<>();

        if (!str.equals("null")) {
            String digits = str.substring(1, str.length() - 1);
            String[] strArr = digits.split("\\|");
            for (String s : strArr) {
                list.add(Integer.parseInt(s));
            }
        }

        return list;
    }

    private static String listToString(ArrayList<Integer> list) {
        if (list != null) {
            if (list.size() != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (int i = 0; i < list.size(); i++) {
                    sb.append(list.get(i));
                    if (i < list.size() - 1) {
                        sb.append("|");
                    }
                }
                sb.append("]");
                return sb.toString();
            }
        }
        return "null";
    }


    public static void saveBoards(ArrayList<Board> boards){
        StringBuilder content = new StringBuilder();

        for (Board board : boards) {
            content.append(board.getId()).append(",").append(board.getName()).append(",").append(listToString(board.issuesList));
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
                board.setIssuesList(stringToList(dataStringItem[2]));
                boards.add(board);
            }
         return boards;
    }

}
