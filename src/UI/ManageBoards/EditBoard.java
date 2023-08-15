package UI.ManageBoards;

import Logic.Boards.Board;
import Logic.Boards.BoardController;
import Logic.Projects.ProjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBoard extends JFrame {
    BoardController boardController = new BoardController();

    public EditBoard(Board board){

        setSize(400,400);
        setLayout(null);
        setBounds(0, 0, 400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel boardName = new JLabel("Name");
        boardName.setBounds(53,50,100,30);
        boardName.setFont(new Font("Poppins", Font.BOLD, 17));
        add(boardName);

        JTextField editBoardName = new JTextField(board.getName());
        editBoardName.setBounds(50,80,290,60);
        editBoardName.setFont(new Font("Poppins", Font.BOLD, 17));
        add(editBoardName);

        JButton submit = new JButton("Submit");
        submit.setBounds(50,175,290,80);
        add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = editBoardName.getText();
                boardController.editBoardName(board.getId(), newName);
                setVisible(false);
            }
        });

        JButton delete = new JButton("❌ Delete board");
        delete.setBounds(50,265,290,50);
        add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure to delete " + board.getName() + "?",
                        "Delete?", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {

                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    boardController.removeSingleBoard(board.getId());
                    setVisible(false);
                } else {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });

    }
}
