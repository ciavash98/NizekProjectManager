package UI.ManageProjects;
import Logic.Projects.ProjectController;
import Logic.Users.*;
import Utils.RoundedJTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;

public class AddProject extends JFrame {

    ProjectController projectController;
    UserController userController;
    AddProjectDelegate delegate;

    public AddProject(ProjectController projectController) {
        this.projectController = projectController;
        this.userController = new UserController();

        JFrame addProjectFrame = new JFrame("New Project");
        JPanel addProjectPanel = new JPanel();
        addProjectFrame.setLayout(null);
        addProjectPanel.setLayout(null);

        addProjectFrame.setSize(800,800);
        addProjectFrame.setLocationRelativeTo(null);
        addProjectFrame.setVisible(true);
        addProjectPanel.setSize(800,800);
        addProjectPanel.setBackground(Color.DARK_GRAY);
        addProjectFrame.setContentPane(addProjectPanel);

        JLabel projectNameLabel = new JLabel("Project name: ");
        projectNameLabel.setBounds(43, 90, 100, 20);
        projectNameLabel.setFont(new Font("X Roya", Font.BOLD, 17));
        addProjectPanel.add(projectNameLabel);

        RoundedJTextField projectNameField = new RoundedJTextField(0,30);
        projectNameField.setBounds(40, 116, 300, 30);
        projectNameField.setBackground(new Color(255, 255, 255));
        projectNameField.setFont(new Font("X Traffic", Font.BOLD, 17));
        addProjectPanel.add(projectNameField);

        JLabel selectPOLabel = new JLabel("Select Product Owner:");
        selectPOLabel.setBounds(40, 220, 170, 20);
        selectPOLabel.setFont(new Font("X Roya", Font.BOLD, 17));
        addProjectPanel.add(selectPOLabel);

        JList<User> selectPOBox = new JList(userController.filter(Roles.PRODUCT_OWNER).values().toArray());
        JScrollPane listScrollerPO = new JScrollPane(selectPOBox);
        listScrollerPO.setBounds(40, 250, 350, 300);
        listScrollerPO.setBackground(new Color(255, 255, 255));
        listScrollerPO.setFont(new Font("X Traffic", Font.BOLD, 17));
        addProjectPanel.add(listScrollerPO);
        selectPOBox.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);

        JLabel selectQALabel = new JLabel("Select Quality Assurance:");
        selectQALabel.setBounds(420, 220, 190, 20);
        selectQALabel.setFont(new Font("Poppins", Font.BOLD, 17));
        addProjectPanel.add(selectQALabel);

        JList<User> selectQABox = new JList(userController.filter(Roles.QUALITY_ASSURANCE).values().toArray());
        JScrollPane listScrollerQA = new JScrollPane(selectQABox);
        listScrollerQA.setBounds(420, 250, 350, 300);
        listScrollerQA.setFont(new Font("X Traffic", Font.BOLD, 17));
        addProjectPanel.add(listScrollerQA);
        selectQABox.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);

        JLabel dateNote = new JLabel("When you click on the '+ Create Project'");
        JLabel dateNote2 = new JLabel("your locate date will be assign to project.");

        dateNote.setBounds((addProjectPanel.getWidth() / 2) - (280 / 2) , 600, 280, 20);
        dateNote.setFont(new Font("Arial", Font.BOLD, 14));
        dateNote2.setBounds((addProjectPanel.getWidth() / 2) - (290 / 2) , 630, 290, 20);
        dateNote2.setFont(new Font("Arial", Font.BOLD, 14));
        dateNote.setFont(new Font("Poppins", Font.BOLD, 13));
        dateNote2.setFont(new Font("Poppins", Font.BOLD, 13));
        addProjectPanel.add(dateNote);
        addProjectPanel.add(dateNote2);

        JButton createButton = new JButton("+ Create Project");
        createButton.setFont(new Font("Poppins", Font.BOLD, 13));
        createButton.setBounds((addProjectPanel.getWidth() / 2) - (300 / 2), 680, 300, 60);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = projectNameField.getText();
                if (!name.equals("")) {
                    int id = projectController.getAllProjects().size();
                    projectController.addProject(id, name);

                    for (User user : selectPOBox.getSelectedValuesList()) {
                        projectController.assign(id, user.getId(), user.getRole());
                    }

                    for (User user1 : selectQABox.getSelectedValuesList()) {
                        projectController.assign(id, user1.getId(), user1.getRole());
                    }

                    addProjectFrame.setVisible(false);
                    delegate.onProjectAdded();
                } else {
                    System.out.println("Project name is empty.");
                }
            }
        });
        addProjectPanel.add(createButton);
    }
}
