package UI.ManageProjects;
import Logic.Projects.ProjectController;
import Logic.Users.*;
import Utils.RoundedJTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AddProject extends JFrame {

    ProjectController projectController;
    UserController userController;
    AddProjectDelegate delegate;
    static RoundedJTextField projectNameField = new RoundedJTextField(0,30);


    public AddProject(ProjectController projectController) {
        this.projectController = projectController;
        this.userController = new UserController();

        JFrame addProjectFrame = new JFrame("New Project");
        JPanel addProjectPanel = new JPanel();
        addProjectFrame.setLayout(null);
        addProjectPanel.setLayout(null);

        addProjectFrame.setSize(400,300);
        addProjectFrame.setLocationRelativeTo(null);
        addProjectFrame.setVisible(true);
        addProjectPanel.setSize(400,300);
        addProjectPanel.setBackground(Color.DARK_GRAY);
        addProjectFrame.setContentPane(addProjectPanel);

        JLabel projectNameLabel = new JLabel("Project name: ");
        projectNameLabel.setBounds(43, 50, 120, 20);
        projectNameLabel.setFont(new Font("Poppins", Font.BOLD, 15));
        addProjectPanel.add(projectNameLabel);

        projectNameField.setForeground(Color.BLACK);
        projectNameField.setBounds(40, 80, 320, 30);
        projectNameField.setBackground(Color.WHITE);
        projectNameLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        addProjectPanel.add(projectNameField);

//
//        JLabel selectPOLabel = new JLabel("Select Product Owner:");
//        selectPOLabel.setBounds(40, 170, 170, 20);
//        selectPOLabel.setFont(new Font("Poppins", Font.BOLD, 15));
//        addProjectPanel.add(selectPOLabel);
//
//        JList<User> selectPOBox = new JList(userController.filter(Roles.PRODUCT_OWNER).values().toArray());
//        JScrollPane listScrollerPO = new JScrollPane(selectPOBox);
//        listScrollerPO.setBounds(40, 200, 720, 100);
//        listScrollerPO.setBackground(new Color(255, 255, 255));
//        listScrollerPO.setFont(new Font("Poppins", Font.BOLD, 15));
//        addProjectPanel.add(listScrollerPO);
//        selectPOBox.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
//
//        JLabel selectQALabel = new JLabel("Select Quality Assurance:");
//        selectQALabel.setBounds(40, 330, 220, 20);
//        selectQALabel.setFont(new Font("Poppins", Font.BOLD, 15));
//        addProjectPanel.add(selectQALabel);
//
//        JList<User> selectQABox = new JList(userController.filter(Roles.QUALITY_ASSURANCE).values().toArray());
//        JScrollPane listScrollerQA = new JScrollPane(selectQABox);
//        listScrollerQA.setBounds(40, 360, 720, 100);
//        listScrollerQA.setFont(new Font("Poppins", Font.BOLD, 15));
//        addProjectPanel.add(listScrollerQA);
//        selectQABox.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
//

        JLabel dateNote = new JLabel("When you click on the '+ Create Project'");
        JLabel dateNote2 = new JLabel("your locate date will be assign to project.");

        dateNote.setBounds((addProjectPanel.getWidth() / 2) - (280 / 2) , 150, 290, 20);
        dateNote2.setBounds((addProjectPanel.getWidth() / 2) - (290 / 2) , 170, 300, 20);
        dateNote.setFont(new Font("Poppins", Font.BOLD, 13));
        dateNote2.setFont(new Font("Poppins", Font.BOLD, 13));
        addProjectPanel.add(dateNote);
        addProjectPanel.add(dateNote2);

        JButton createButton = new JButton("+ Create Project");
        createButton.setFont(new Font("Poppins", Font.BOLD, 13));
        createButton.setBounds((addProjectPanel.getWidth() / 2) - (300 / 2), 200, 300, 60);

        InputMap inputMap = createButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = createButton.getActionMap();
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, "selectButton");
        actionMap.put("selectButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createButton.doClick();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = projectNameField.getText();
                if (!name.equals("")) {
                    int id = projectController.getAllProjects().size();
                    projectController.addProject(id, name);
                    addProjectFrame.setVisible(false);
                    delegate.onProjectAdded();
                } else {
                    projectNameField.setText("Please enter a project name");
                }
            }
        });
        addProjectPanel.add(createButton);
    }
}
