package Logic.Projects;
import Logic.Boards.BoardController;
import Logic.Users.Roles;
import java.util.ArrayList;

public class ProjectController {
    BoardController boardController = new BoardController();

    public static ArrayList<Project> getAllProjects(){
        return ProjectRepository.readProjects();
    }

    public void assign(int projectId, int userId, Roles userRole) {
        ArrayList<Project> projects = getAllProjects();

        for (Project project : projects) {
            if (project.getId() == projectId) {
                switch (userRole) {
                    case PRODUCT_OWNER:
                        if (project.getPosList() == null) {
                            ArrayList<Integer> pos = new ArrayList<>();
                            pos.add(userId);
                            project.setPosList(pos);
                        } else {
                            ArrayList<Integer> pos = project.getPosList();
                            pos.add(userId);
                            project.setPosList(pos);
                        }
                        break;

                    case QUALITY_ASSURANCE:
                        if (project.getQasList() == null) {
                            ArrayList<Integer> qas = new ArrayList<>();
                            qas.add(userId);
                            project.setQasList(qas);
                        } else {
                            ArrayList<Integer> qas = project.getQasList();
                            qas.add(userId);
                            project.setQasList(qas);
                        }
                        break;
                }

                break;
            }
        }
        ProjectRepository.saveProject(projects); // Save project list
    }

    public void addBoardToProject(int projectId,int boardId) {

        ArrayList<Project> projects = readProjects();
        for (Project p : projects) {
            if(p.getId() == projectId){
                p.boardList.add(boardId);
                projects.add(p);
            }
        }
        saveProjects(projects);
    }

    public void addProject(int id, String name) {

        int boardID = boardController.addBoard("Default");
        ArrayList<Integer> boards = new ArrayList<>();
        boards.add(boardID);

        Project project = new Project(id, name, boards);
        ProjectRepository.addProject(project);
    }

    public void editProjectName(int id, String name) {
        ArrayList<Project> projects = ProjectRepository.readProjects();
        for (Project p : projects) {
            if(p.getId() == id){
                p.getName().replace(p.getName(), name);
            }
        }
        ProjectRepository.saveProject(projects);
    }

    public void removeProject(int id) {
        ArrayList<Project> projectList = getAllProjects();

        for (Project p: projectList) {
            if(p.getId() == id){
                for (int boardID: p.boardList) {
                    boardController.removeBoard(boardID);
                }
                projectList.remove(id);
            }
        }
        ProjectRepository.saveProject(projectList);
    }

    public void saveProjects(ArrayList<Project> project) {
        ProjectRepository.saveProject(project);
    }

    public ArrayList<Project> readProjects() {
        ArrayList<Project> projects = ProjectRepository.readProjects();
        return projects;
    }

    public static Project findById(int id){
        ArrayList<Project> projects = getAllProjects();
        for (Project project : projects) {
            if (project.getId() == (id)){
                return project;
            }
        }
        return null;
    }
}
