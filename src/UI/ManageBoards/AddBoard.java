package UI.ManageBoards;
import Logic.Boards.BoardController;
import Logic.Projects.Project;
import Logic.Projects.ProjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBoard extends JFrame {

    BoardController boardController = new BoardController();

    JPanel addNewBoardPanel = new JPanel();
    JLabel boardNameLabel = new JLabel("Board name: ");
    JTextField boardNameField = new JTextField();
    JButton createButton = new JButton("+ Create Board");
    Project project;
    ProjectController projectController = new ProjectController();
    public AddBoard(Project project) {
        this.project = project;

        setLayout(null);
        setTitle("Add New Board");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        addNewBoardPanel.setLayout(null);
        addNewBoardPanel.setBounds(0,0,400,400);
        addNewBoardPanel.setBackground(Color.DARK_GRAY);
        add(addNewBoardPanel);

        boardNameLabel.setBounds((addNewBoardPanel.getWidth() / 2) - (120 / 2), 110, 120, 20);
        boardNameLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        addNewBoardPanel.add(boardNameLabel);

        boardNameField.setBounds((addNewBoardPanel.getWidth() / 2) - (300 / 2), 150, 300, 30);
        boardNameField.setBackground(new Color(255, 255, 255));
        boardNameField.setFont(new Font("Poppins", Font.BOLD, 17));
        addNewBoardPanel.add(boardNameField);

        createButton.setFont(new Font("Poppins", Font.BOLD, 17));
        createButton.setBounds((addNewBoardPanel.getWidth() / 2) - (350 / 2), 270, 350, 50);
        addNewBoardPanel.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = boardNameField.getText();
                projectController.addBoardToProject(project.getId(), name);
                boardNameField.setText("");
                setVisible(false);
            }
        });
        setVisible(true);
    }
}
