package UI.ManageBoards;

import Logic.Boards.Board;
import Logic.Boards.BoardController;
import Logic.Boards.BoardRepository;
import Logic.Projects.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBoard extends JFrame {

    BoardController boardController = new BoardController();

    public AddBoard() {

        setLayout(null);
        setTitle("Add New Board");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel addNewBoardPanel = new JPanel();

        addNewBoardPanel.setLayout(null);
        addNewBoardPanel.setBounds(0,0,400,400);
        addNewBoardPanel.setBackground(Color.DARK_GRAY);
        add(addNewBoardPanel);

        JLabel boardNameLabel = new JLabel("Board name: ");
        boardNameLabel.setBounds((addNewBoardPanel.getWidth() / 2) - (100 / 2), 110, 100, 20);
        boardNameLabel.setFont(new Font("X Roya", Font.BOLD, 17));
        addNewBoardPanel.add(boardNameLabel);

        JTextField boardNameField = new JTextField();
        boardNameField.setBounds((addNewBoardPanel.getWidth() / 2) - (300 / 2), 150, 300, 30);
        boardNameField.setBackground(new Color(255, 255, 255));
        boardNameField.setFont(new Font("X Traffic", Font.BOLD, 17));
        addNewBoardPanel.add(boardNameField);

        JButton createButton = new JButton("+ Create Board");
        createButton.setBounds((addNewBoardPanel.getWidth() / 2) - (350 / 2), 270, 350, 50);
        addNewBoardPanel.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = boardNameField.getText();
                boardController.addBoard(name);
                boardNameField.setText("");
                setVisible(false);
            }

        });
        setVisible(true);
    }
}
