package Logic.Projects;
import Logic.Boards.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectRepository {

    public static void addProject(Project project) {
        ArrayList<String > projectList = readProjectFile("Projects.csv");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        projectList.add(project.getId()
                + "," + project.getName()
                + "," + dateFormatter.format(project.date)
                + "," + listToString(project.getBoardList())
                + "," + listToString(project.getPosList())
                + "," + listToString(project.getQasList()));

        StringBuilder content = new StringBuilder();

        for (String string : projectList) {
            content.append(string);
            content.append("\n");
        }
        try {
            saveFile("Projects.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Project> readProjects() {
        ArrayList<Project> projects = new ArrayList<>();

        ArrayList<String> projectsString = readProjectFile("Projects.csv");
        for (String data : projectsString) {
            String[] dataStringItem = data.split(",");

            int id = Integer.parseInt(dataStringItem[0]);
            String name = dataStringItem[1];
            Date date = parseDate(dataStringItem[2]);
            ArrayList<Integer> boardList = stringToList(dataStringItem[3]);
            ArrayList<Integer> posList = stringToList(dataStringItem[4]);
            ArrayList<Integer> qasList = stringToList(dataStringItem[5]);

            Project project = new Project(id, name, boardList);
            project.setPosList(posList);
            project.setQasList(qasList);
            project.setDate(date);

            projects.add(project);
        }

        return projects;
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + dateString);
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


    public static void saveFile(String fileName, String content) throws FileSystemException {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new FileSystemException("Failed to save file: " + fileName);
        }
    }

    public static void saveProject(ArrayList<Project> projects){
        StringBuilder content = new StringBuilder();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Project project: projects) {

            content.append(project.getId())
                    .append(",")
                    .append(project.getName())
                    .append(",")
                    .append(dateFormatter.format(project.getDate()))
                    .append(",")
                    .append(listToString(project.getBoardList()))
                    .append(",")
                    .append(listToString(project.getPosList()))
                    .append(",")
                    .append(listToString(project.getQasList()));
            content.append("\n");
        }

        try {
            saveFile("Projects.csv", content.toString());
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }

    }

    // Always should be private
    private static ArrayList<String> readProjectFile(String fileName) {
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
}
