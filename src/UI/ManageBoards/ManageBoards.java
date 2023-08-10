//package UI.ManageBoards;
//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class ManageBoards extends JPanel {
//    Border borderLine = BorderFactory.createLineBorder(Color.black);
//    AddBoard addBoard = new AddBoard();
//    public ManageBoards(){
//        JPanel allBoardsPanel = new JPanel();
//
//
//        setBackground(Color.WHITE);
//        setBounds(250,50,850,750);
//        setBorder(borderLine);
//        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3)));
//        setLayout(null);
//        setVisible(true);
//        allBoardsPanel.setLayout(null);
//        allBoardsPanel.setBounds(0, 0, 850, 750);
//        add(allBoardsPanel);
//
//        JButton board0 = new JButton("felaan board");
//        board0.setBounds(40, 40, 230, 230);
//        board0.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            new ShowBoard();
//            setVisible(true);
//            }
//        });
//        allBoardsPanel.add(board0);
//
//        JButton addNewBoardButton = new JButton("+ Add New Board");
//        addNewBoardButton.setBounds((getWidth() / 2) - (820 / 2), 650, 820, 45);
//        addNewBoardButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            addBoard.addBoard();
//            }
//        });
//        allBoardsPanel.add(addNewBoardButton);
//
//    }
//}
