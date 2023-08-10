package UI.ManageProjects;
import Logic.Projects.Project;
import Logic.Projects.ProjectController;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageProjects extends JPanel implements AddProjectDelegate {

    ProjectController projectController = new ProjectController();
    Border borderLine = BorderFactory.createLineBorder(Color.black);
    AddProject addProject;
    public ManageProjects() {
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setBounds(248, 80, 820, 660);
//        scrollPane.setBorder(null);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setBackground(Color.WHITE);
        setBounds(248, 80, 820, 660);
        setBorder(borderLine);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3)));

//        add(scrollPane);
        setLayout(null);
        setVisible(true);
        setupView();
    }

    private void setupView() {
        final double margin = 28.0;
        double x = margin;
        int y = 28;
        int buttonSize = 170;

        ArrayList<Project> projects = projectController.readProjects();
        for (Project project : projects) {
            if (x + buttonSize >= this.getWidth()) {
                y += buttonSize + margin;
                x = margin;
            }

            JButton projectsButton = new JButton(project.getName());
            projectsButton.setBounds((int) x , y, buttonSize, buttonSize);
            add(projectsButton);
            projectsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ShowProjectInfo(project);
                }
            });
            x += buttonSize + margin;
            add(projectsButton);
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10,585,805,70);
        buttonPanel.setLayout(null);
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.WHITE);
        add(buttonPanel);

        JButton addNewProject = new JButton("+ Add New Project");
        addNewProject.setBounds((buttonPanel.getWidth() / 2) - (760 / 2), 25, 760, 45);
        addNewProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProject = new AddProject(projectController);
                function();
            }
        });
        buttonPanel.add(addNewProject);
    }

    @Override
    public void onProjectAdded() {
        setupView();
    }

    private void function() {
        addProject.delegate = this;
    }
}
