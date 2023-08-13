package UI.ManageProjects;
import Logic.Projects.Project;
import Logic.Projects.ProjectController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class ManageProjects extends JPanel implements AddProjectDelegate {

    ProjectController projectController = new ProjectController();
    private final int cornerRadius = 60;
    AddProject addProject;

    public ManageProjects() {

        setBounds(248, 80, 820, 660);

        setLayout(null);
        setVisible(true);
        setupView();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(224, 225, 221), 0, height, new Color(205, 213, 224));
        g2d.setPaint(gradientPaint);

        GradientPaint gradientButton = new GradientPaint(0, 0, new Color(224, 225, 221), 0, height, new Color(205, 213, 224));
        g2d.setPaint(gradientButton);

        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height - cornerRadius);
        path.quadTo(width, height, width - cornerRadius, height);
        path.lineTo(0, height);
        path.closePath();
        g2d.fill(path);

        g2d.dispose();
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
        buttonPanel.setOpaque(false);
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.WHITE);
        add(buttonPanel);

        JButton addNewProject = new JButton("+ Add New Project");
        addNewProject.setBounds((buttonPanel.getWidth() / 2) - (760 / 2) - 10, 15, 760, 45);
        addNewProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProject = new AddProject(projectController);
                updatePanel();
            }
        });
        buttonPanel.add(addNewProject);
    }

    @Override
    public void onProjectAdded() {
        setupView();
    }

    private void updatePanel() {
        addProject.delegate = this;
    }
}
